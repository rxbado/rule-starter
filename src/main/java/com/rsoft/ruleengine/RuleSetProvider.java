package com.rsoft.ruleengine;

import java.util.List;
import java.util.Map;

/**
 * RuleSet Provider.
 * 
 * @author bado
 *
 */
public interface RuleSetProvider {
    public Map<String, List<Rule>> getRuleSet();

    public List<Rule> getRuleSetByScene(String scene);

}
