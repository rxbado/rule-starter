package com.rsoft.ruleengine.endpoint;

import com.rsoft.ruleengine.Rule;
import com.rsoft.ruleengine.RuleLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;

import java.util.List;
import java.util.Map;

/**
 * 自定义刷新端点.
 * 
 * @author bado
 *
 */
@RestControllerEndpoint(id = "rules")
public class RuleEngineEndpoint {
    @Autowired
    private RuleLoader ruleLoader;

    public RuleEngineEndpoint(RuleLoader loader) {
        this.ruleLoader = loader;
    }

    /**
     * 刷新全部规则.
     * 
     * @return
     */
    @ReadOperation
    public Map<String, List<Rule>> reload() {
        return ruleLoader.reload();
    }

    /**
     * 刷新特定规则.
     * 
     * @param scene
     *            指定场景.
     * @return
     */
    @ReadOperation
    public List<Rule> reloadScene(@Selector String scene) {
        return ruleLoader.reloadScene(scene);
    }
}
