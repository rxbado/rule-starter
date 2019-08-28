package com.rsoft.ruleengine;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 规则引擎配置.
 * 
 * @author rsoft
 */
@ConfigurationProperties("ruleengine")
public class RuleEngineProperties {
    private Boolean enabled = true;

    private String namespace = "default";

    private RuleCache cache;

    private class RuleCache {
        private String mode = "local";// local,redis
        private String refreshEndpoint;
    }

    private String endpointsWebExposureInclude;

}
