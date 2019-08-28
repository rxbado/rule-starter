package com.rsoft.ruleengine;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 规则信息.
 * 
 * @author rsoft
 *
 */
@Data
@ToString
public class RuleInfo {
    /**
     * 规则场景.
     */
    private String scene;
    /**
     * 规则key.
     */
    private String rulekey;
    /**
     * 规则内容(对应规则文件,例如*.drl文件内容).
     */
    private String content;
    /**
     * 规则优先级,按场景排序.
     */
    private Integer priority;
    /**
     * 规则生效时间.
     */
    private Date effective;
    /**
     * 规则失效/超期时间.
     */
    private Date expires;
}
