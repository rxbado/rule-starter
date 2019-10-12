package com.rsoft.ruleengine;

import java.util.List;
import java.util.Map;

/**
 * 规则装载器.
 * 
 * @author bado
 *
 */
public interface RuleLoader {
    public List<Rule> reloadScene(String scene);

    public Map<String, List<Rule>> reload();
}
