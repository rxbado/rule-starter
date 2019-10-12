package com.rsoft.ruleengine.impl;

import com.rsoft.ruleengine.Rule;
import com.rsoft.ruleengine.RuleSetProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Null RuleSet.
 * 
 * @author bado
 *
 */
public class NullRuleSetProvider implements RuleSetProvider {
    private static final List<Rule> rules = new ArrayList<Rule>();
    private static final Map<String, List<Rule>> ruleset = new HashMap<String, List<Rule>>();

    @Override
    public List<Rule> getRuleSetByScene(String scene) {
        return rules;
    }

    @Override
    public Map<String, List<Rule>> getRuleSet() {
        return ruleset;
    }

}
