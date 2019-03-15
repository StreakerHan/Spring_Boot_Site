package com.hyp.model;

/**
 * 

* @Title: OptionsDomain  

* @Description:  用户实体类 

* @author HanYupeng  

* @date 2018-06-15 10:29
 */
public class OptionsDomain {

    /** 名称 */
    private String name;
    /** 内容 */
    private String value;
    /** 备注 */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
