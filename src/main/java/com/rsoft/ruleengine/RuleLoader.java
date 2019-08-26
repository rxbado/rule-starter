package com.rsoft.ruleengine;

import com.rsoft.ruleengine.model.RuleInfo;

import java.util.List;
import java.util.Map;

/**
 * 规则加载器.
 * 
 * @author rsoft
 *
 */
public interface RuleLoader {
    public List<RuleInfo> reloadScene(String scene);

    public Map<String, List<RuleInfo>> reload();
}
