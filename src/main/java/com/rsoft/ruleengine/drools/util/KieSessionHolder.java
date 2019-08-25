package com.rsoft.ruleengine.drools.util;

import com.rsoft.ruleengine.drools.DroolsFileSystemRuleLoader;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 获取Drools的会话工具.
 * 
 * @author rsoft
 *
 */
public class KieSessionHolder {
    @Autowired
    private DroolsFileSystemRuleLoader ruleLoader;

    public KieSession getKieSessionByScene(String scene) {
        return ruleLoader.getKieContainerByScene(scene).getKieBase().newKieSession();
    }

}
