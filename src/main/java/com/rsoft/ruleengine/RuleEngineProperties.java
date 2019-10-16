package com.rsoft.ruleengine;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 规则引擎配置.
 * 
 * @author bado
 */
@ConfigurationProperties("re")
public class RuleEngineProperties {
    private Boolean enabled = true;
    private String namespace = "default";
    private RuleCache caches;

    private class RuleCache {
        public static final String RULE_CACHE_LOCAL = "local";
        public static final String RULE_CACHE_REDIS = "redis";

        private String mode = RULE_CACHE_LOCAL;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public RuleCache getCaches() {
        return caches;
    }

    public void setCaches(RuleCache caches) {
        this.caches = caches;
    }

}
