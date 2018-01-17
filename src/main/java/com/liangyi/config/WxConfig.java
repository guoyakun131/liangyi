package com.liangyi.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;

/**
 * 微信小程序相关配置信息
 */

public class WxConfig implements WXPayConfig {
    @Override
    public String getAppID() {
        return "wxbc67da499c16c2f7";
    }

//    @Override
//    public String getMchID() {
//        return "1429475902";
//    }

    @Override
    public String getMchID() {
        return "1429475902";
    }

    @Override
    public String getKey() {
        return "handanliangyikejiyouxiangongsi88";
    }

    @Override
    public InputStream getCertStream() {
        return null;
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
