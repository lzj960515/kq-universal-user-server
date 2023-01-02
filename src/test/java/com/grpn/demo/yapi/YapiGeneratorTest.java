package com.grpn.demo.yapi;

import com.kqinfo.universal.yapi.generator.YapiGenerator;
import org.junit.jupiter.api.Disabled;

/**
 * yapi生成
 * @author Zijian Liao
 * @since 1.0.0
 */
@Disabled
public class YapiGeneratorTest {

    /**
     * yapi中的项目token yapi -> 项目 -> 设置 -> token配置
     */
    private static final String TOKEN = "1234";
    /**
     * 接口包路径
     */
    private static final String BASE_PACKAGES = "com.grpn.demo.controller";

    public static void main(String[] args) {
        YapiGenerator.generateYapi(BASE_PACKAGES, "https://yapi.xxx.com", TOKEN, "yapi", true);
    }
}
