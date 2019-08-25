package com.rsoft.ruleengine;

import com.rsoft.ruleengine.model.RuleInfo;

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
public class NullRuleSetSource implements RuleSetSource {

    @Override
    public List<RuleInfo> getRuleSetByScene(String scene) {
        return new ArrayList<RuleInfo>();
    }

    @Override
    public Map<String, List<RuleInfo>> getRuleSetAsMap() {
        return new HashMap<String, List<RuleInfo>>();
    }

}
