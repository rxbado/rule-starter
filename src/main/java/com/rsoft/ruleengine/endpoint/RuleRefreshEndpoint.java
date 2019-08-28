package com.rsoft.ruleengine.endpoint;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.RuleLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.List;
import java.util.Map;

/**
 * 自定义刷新端点.
 * 
 * @author rsoft
 *
 */
@Endpoint(id = "rules")
public class RuleRefreshEndpoint {
    @Autowired
    private RuleLoader ruleLoader;

    /**
     * 刷新全部规则.
     * 
     * @return
     */
    @ReadOperation
    public Map<String, List<RuleInfo>> reload() {
        return ruleLoader.reload();
    }

    /**
     * 刷新特定规则.
     * 
     * @param scene
     *            指定场景.
     * @return
     *//*
       * @ReadOperation public List<RuleInfo> reloadScene(String scene) { return ruleLoader.reloadScene(scene); }
       */
}
