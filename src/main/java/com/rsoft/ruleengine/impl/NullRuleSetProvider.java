package com.rsoft.ruleengine.impl;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.RuleSetProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空的规则元数据.
 * 
 * @author rsoft
 *
 */
public class NullRuleSetProvider implements RuleSetProvider {

    @Override
    public List<RuleInfo> getRuleSetByScene(String scene) {
        return new ArrayList<RuleInfo>();
    }

    @Override
    public Map<String, List<RuleInfo>> getRuleSetAsMap() {
        return new HashMap<String, List<RuleInfo>>();
    }

}