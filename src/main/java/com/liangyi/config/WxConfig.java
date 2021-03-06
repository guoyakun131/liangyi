package com.liangyi.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 微信小程序相关配置信息
 */

public class WxConfig implements WXPayConfig {
    @Override
    public String getAppID() {
        //return "wxbc67da499c16c2f7";
        return "wxcbfd0a5d8b3eec2e";
    }

    @Override
    public String getMchID() {
        //return "1429475902";
        return "1395566102";
    }

    @Override
    public String getKey() {
       // return "handanliangyikejiyouxiangongsi88";
        return "handanliangyikejiyouxiangongsi66";
    }

    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream() {
        try {
            return new FileInputStream(new File("/usr/Java/cert(4)/apiclient_cert.p12"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }


}
