package com.rsoft.ruleengine;

import com.rsoft.ruleengine.endpoint.RuleEngineEndpoint;
import com.rsoft.ruleengine.impl.NullRuleSetProvider;
import com.rsoft.ruleengine.impl.drools.DroolsClasspathRuleLoader;
import com.rsoft.ruleengine.impl.drools.DroolsFileSystemRuleLoader;
import com.rsoft.ruleengine.impl.drools.DroolsRuleRunner;
import com.rsoft.ruleengine.impl.drools.KieSessionHolder;

import org.kie.api.KieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则引擎自动配置.
 * 
 * @author bado
 *
 */
@Configuration
@EnableConfigurationProperties(RuleEngineProperties.class)
public class RuleEngineAutoConfiguration {
    @Autowired
    private RuleEngineProperties properties;

    @Bean
    @ConditionalOnMissingBean
    RuleSetProvider ruleSetProvider() {
        return new NullRuleSetProvider();
    }

    @Configuration
    @ConditionalOnClass({ KieServices.class, DroolsFileSystemRuleLoader.class, DroolsClasspathRuleLoader.class })
    protected static class DroolsRuleConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = "re", value = "enabled", havingValue = "true")
        DroolsFileSystemRuleLoader droolsRuleLoader() {
            return new DroolsFileSystemRuleLoader();
        }

        @Bean
        RuleRunner ruleRunner() {
            return new DroolsRuleRunner();
        }

        @Bean
        KieSessionHolder kieSessionHolder() {
            return new KieSessionHolder();
        }
    }

    @Configuration
    @ConditionalOnClass(Health.class)
    protected static class RuleEngineEndpointConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        public RuleEngineEndpoint ruleRefreshEndpoint(RuleLoader ruleLoader) {
            return new RuleEngineEndpoint(ruleLoader);
        }
    }
}
