package com.rsoft.ruleengine;

import com.rsoft.ruleengine.model.RuleInfo;

import java.util.List;
import java.util.Map;

/**
 * 规则数据源.
 * 
 * @author rsoft
 *
 */
public interface RuleSetSource {
    public List<RuleInfo> getRuleSetByScene(String scene);

    public Map<String, List<RuleInfo>> getRuleSetAsMap();
}
