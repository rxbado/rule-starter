package com.rsoft.ruleengine.impl.drools;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.impl.AbstractRuleLoader;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import java.util.List;

/**
 * 基于类路径的规则加载器.
 * 
 * @author rsoft
 *
 */
public class DroolsClasspathRuleLoader extends AbstractRuleLoader {

    @Override
    public void reload(String scene, List<RuleInfo> rules) {
        // TODO something
    }

    private KieContainer getKieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        return kieContainer;
    }
}
