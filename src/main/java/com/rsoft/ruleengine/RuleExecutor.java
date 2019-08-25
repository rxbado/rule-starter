package com.rsoft.ruleengine;

import java.io.Serializable;

/**
 * 规则执行器.
 * 
 * @author rsoft
 *
 * @param <T>
 *            rule fact.
 */
@FunctionalInterface
public interface RuleExecutor<T extends Serializable> {
    int execute(String scene, T fact);
}
