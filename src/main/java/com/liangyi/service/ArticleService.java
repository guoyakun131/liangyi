package com.liangyi.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.liangyi.Utils.IpUtil;
import com.liangyi.Utils.PayUtil;
import com.liangyi.config.Config;
import com.liangyi.config.WxConfig;
import com.liangyi.entity.Article;
import com.liangyi.entity.User;
import com.liangyi.mapper.ArticleMapper;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图文
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    private int article_id;
    /**
     * 查询图文列表
     *
     * @param type
     * @return
     */
    public List<Article> articlesList(int type) {
        if (type == 0) {
            return articleMapper.article();
        } else {
            return articleMapper.articleType(type);
        }

    }

    /**
     * 图文详情
     *
     * @param id
     * @return
     */
    public Map<String, Object> article(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("article", articleMapper.articleInfo(id));
        map.put("goods", articleMapper.goods(id));
        map.put("user", articleMapper.avatar(id));
        return map;
    }


    /**
     * 浏览
     *
     * @param session_id
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void userBrowse(String session_id, int id) {
        User user = userMapper.userId(session_id);
        //流量加1
        articleMapper.browse(id);
        //查看用户是否有浏览记录
        if (articleMapper.isUserBrowse(user.getId(), id) == null || articleMapper.isUserBrowse(user.getId(), id) == 0) {
            articleMapper.userBrowse(user.getId(), id);
        }
    }


    public Map<String, String> order(HttpServletRequest request, String session_id, int id, String jine) throws Exception {
        article_id = id;
//订单详情
        double shangjin = Double.valueOf(jine);
        double d = shangjin * 100;
        int i = (new Double(d)).intValue();
        System.out.println(d);
        String sum = String.valueOf(i);
        System.out.println("sum:" + sum);
        WxConfig wxConfig = new WxConfig();
        WXPay wxPay = new WXPay(wxConfig);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //openId
        String openid = userMapper.openId(session_id);
        HashMap<String, String> data = new HashMap<>();
        data.put("appid", wxConfig.getAppID());
        data.put("openid", openid);
        data.put("body", Config.MerchantsName + "-打赏");
        data.put("out_trade_no", sdf.format(date).toString());
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", sum);
        data.put("spbill_create_ip", IpUtil.getIpAddr(request));
        data.put("notify_url", "https://qubing.net.cn/ly/api/article/notify");
        data.put("trade_type", "JSAPI");
        System.out.println("data:" + data);
        Map<String, String> resp = null;
        try {
            resp = wxPay.unifiedOrder(data);
            System.out.println("resp:" + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回状态码
        String return_code = (String) resp.get("return_code");
        // 返回给小程序端需要的参数
        Map<String, String> response = new HashMap<String, String>();
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            // 返回的预付单信息
            String prepay_id = (String) resp.get("prepay_id");
            String nonce_str = (String) resp.get("nonce_str");
            response.put("nonceStr", nonce_str);
            response.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            System.out.println("timeStamp:" + timeStamp.toString());
            // 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
            response.put("timeStamp", timeStamp + "");
            // 拼接签名需要的参数
            String stringSignTemp = "appId=" + Config.AppId + "&nonceStr=" + nonce_str + "&package=prepay_id="
                    + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
            System.out.println("stringSignTemp:" + stringSignTemp);
            // 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
            System.out.println("执行");
            //签名后一定要小写
            String paySign = PayUtil.sign(stringSignTemp, Config.Key, "UTF-8").toString();
            //微信的sdk生成的是大写，但是前段要小写，文档也没说明，真是坑啊
            //String paySign = WXPayUtil.generateSignature(resp, Config.Key, WXPayConstants.SignType.MD5);//再签名一次
            response.put("paySign", paySign);
        }
        System.out.println("response:" + response);
        return response;
    }

    /**
     * 支付后通知
     * @param request
     * @param response
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // sb为微信返回的xml
        String notifyData = sb.toString(); // 支付结果通知的xml格式数据
        String resXml = "";

        System.out.println("接收到的报文：" + notifyData);

        WxConfig config = new WxConfig();
        WXPay wxpay = new WXPay(config);
        // 转换成map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);

        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
            System.out.println("签名正确");
            // 签名正确
            // 进行处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
            articleMapper.bounty(article_id);
            // 通知微信服务器已经支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

        } else {
            System.out.println("签名错误");
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
