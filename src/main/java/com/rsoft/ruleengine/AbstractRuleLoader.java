package com.rsoft.ruleengine;

import com.rsoft.ruleengine.model.RuleInfo;

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
    private RuleSetSource ruleSetService;

    @Override
    public void run(String... args) throws Exception {
        reload();
    }

    public abstract void reload(String sceneId, List<RuleInfo> rules);

    /**
     * 刷新所有规则.
     */
    public void reload() {
        Map<String, List<RuleInfo>> rules = ruleSetService.getRuleSetAsMap();
        for (Map.Entry<String, List<RuleInfo>> entry : rules.entrySet()) {
            String scene = entry.getKey();
            reload(scene, entry.getValue());
        }
    }

    /**
     * 刷新特定场景规则.
     */
    public void reloadScene(String scene) {
        List<RuleInfo> ruleInfos = ruleSetService.getRuleSetByScene(scene);
        reload(scene, ruleInfos);
        System.out.println("reload success");
    }

}
