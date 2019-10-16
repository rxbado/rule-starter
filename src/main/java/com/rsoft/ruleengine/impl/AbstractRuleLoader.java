package com.rsoft.ruleengine.impl;

import com.rsoft.ruleengine.Rule;
import com.rsoft.ruleengine.RuleEngineProperties;
import com.rsoft.ruleengine.RuleLoader;
import com.rsoft.ruleengine.RuleSetProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Map;

/**
 * 规则加载器模板.
 * 
 * @author bado
 *
 */
public abstract class AbstractRuleLoader implements RuleLoader, CommandLineRunner {
    @Autowired
    private RuleSetProvider provider;
    @Autowired
    private RuleEngineProperties properties;

    @Override
    public void run(String... args) throws Exception {
        reload();
    }

    public abstract void reload(String scene, List<Rule> rules);

    /**
     * 刷新所有规则.
     */
    public Map<String, List<Rule>> reload() {
        Map<String, List<Rule>> rules = provider.getRuleSet();
        rules.entrySet().stream().forEach((rule) -> {
            reload(rule.getKey(), rule.getValue());
        });

        return rules;
    }

    /**
     * 刷新特定场景规则.
     */
    public List<Rule> reloadScene(String scene) {
        List<Rule> ruleInfos = provider.getRuleSetByScene(scene);
        reload(scene, ruleInfos);
        System.out.println("reload success");

        return ruleInfos;
    }

    protected String getNamespace() {
        return properties.getNamespace();
    }
}
