package com.rsoft.ruleengine.impl.drools;

import com.rsoft.ruleengine.Rule;
import com.rsoft.ruleengine.impl.AbstractRuleLoader;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import java.util.List;

/**
 * 基于类路径的规则加载器.
 * 
 * @author bado
 * @TODO 待实现
 */
public class DroolsClasspathRuleLoader extends AbstractRuleLoader {

    @Override
    public void reload(String scene, List<Rule> rules) {
        // TODO something
    }

    private KieContainer getKieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        return kieContainer;
    }
}
