package com.rsoft.ruleengine;

import com.rsoft.ruleengine.drools.DroolsFileSystemRuleLoader;
import com.rsoft.ruleengine.drools.DroolsRuleExecutor;
import com.rsoft.ruleengine.drools.util.KieSessionHolder;
import com.rsoft.ruleengine.endpoint.RuleRefreshEndpoint;
import com.rsoft.ruleengine.endpoint.RuleSceneRefreshEndpoint;

import org.kie.api.KieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则引擎自动配置.
 * 
 * @author rsoft
 *
 */
@Configuration
@ConditionalOnClass(DroolsFileSystemRuleLoader.class)
@EnableConfigurationProperties(RuleEngineProperties.class)
public class RuleEngineAutoConfiguration {
    @Autowired
    private RuleEngineProperties properties;

    @Bean
    @ConditionalOnMissingBean
    RuleSetSource ruleSetService() {
        return new NullRuleSetSource();
    }

    @Configuration
    @ConditionalOnClass(KieServices.class)
    protected static class DroolsRuleConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = "ruleengine", value = "enabled", havingValue = "true")
        DroolsFileSystemRuleLoader droolsRuleLoader() {
            return new DroolsFileSystemRuleLoader();
        }

        @Bean
        RuleExecutor ruleExecutor() {
            return new DroolsRuleExecutor();
        }

        @Bean
        KieSessionHolder kieSessionHolder() {
            return new KieSessionHolder();
        }
    }

    @Configuration
    static class RuleEngineEndpointConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        public RuleRefreshEndpoint ruleRefreshEndpoint() {
            return new RuleRefreshEndpoint();
        }

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        public RuleSceneRefreshEndpoint ruleSceneRefreshEndpoint() {
            return new RuleSceneRefreshEndpoint();
        }
    }
}
