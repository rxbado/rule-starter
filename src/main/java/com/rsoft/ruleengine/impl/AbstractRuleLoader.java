package com.rsoft.ruleengine.impl;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.RuleLoader;
import com.rsoft.ruleengine.RuleSetProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Map;

/**
 * 规则加载器模板.
 * 
 * @author rsoft
 *
 */
public abstract class AbstractRuleLoader implements RuleLoader, CommandLineRunner {
    @Autowired
    private RuleSetProvider ruleSetService;

    @Override
    public void run(String... args) throws Exception {
        reload();
    }

    public abstract void reload(String scene, List<RuleInfo> rules);

    /**
     * 刷新所有规则.
     */
    public Map<String, List<RuleInfo>> reload() {
        Map<String, List<RuleInfo>> rules = ruleSetService.getRuleSetAsMap();
        for (Map.Entry<String, List<RuleInfo>> entry : rules.entrySet()) {
            String scene = entry.getKey();
            reload(scene, entry.getValue());
        }

        return rules;
    }

    /**
     * 刷新特定场景规则.
     */
    public List<RuleInfo> reloadScene(String scene) {
        List<RuleInfo> ruleInfos = ruleSetService.getRuleSetByScene(scene);
        reload(scene, ruleInfos);
        System.out.println("reload success");

        return ruleInfos;
    }

}
