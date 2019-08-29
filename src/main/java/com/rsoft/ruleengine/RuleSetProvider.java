package com.rsoft.ruleengine;

import java.util.List;
import java.util.Map;

/**
 * RuleSet Provider.
 * 
 * @author rsoft
 *
 */
public interface RuleSetProvider {
    public List<RuleInfo> getRuleSetByScene(String scene);

    public Map<String, List<RuleInfo>> getRuleSetAsMap();
}
