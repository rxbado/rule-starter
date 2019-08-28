package com.rsoft.ruleengine.endpoint;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.RuleLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

import java.util.List;

/**
 * 自定义刷新端点.
 * 
 * @author rsoft
 *
 */
@WebEndpoint(id = "rules-scene")
public class RuleSceneRefreshEndpoint {
    @Autowired
    private RuleLoader ruleLoader;

    /**
     * 刷新特定规则.
     * 
     * @param scene
     *            指定场景.
     * @return
     */
    @ReadOperation
    public List<RuleInfo> reloadScene(@Selector String scene) {
        return ruleLoader.reloadScene(scene);
    }
}
