package com.rsoft.ruleengine.impl.drools;

import com.rsoft.ruleengine.RuleRunner;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Drools规则执行器.
 * 
 * @author bado
 *
 * @param <T>
 *            事实数据.
 */
public class DroolsRuleRunner<T extends Serializable> implements RuleRunner<T> {
    @Autowired
    KieSessionHolder kieSessionHolder;

    @Override
    public int execute(String scene, T fact) {
        KieSession kieSession = kieSessionHolder.getKieSessionByScene(scene);
        // kieSession.setGlobal(identifier, value);
        kieSession.insert(fact);
        int count = kieSession.fireAllRules();

        kieSession.dispose();
        return count;
    }

}
