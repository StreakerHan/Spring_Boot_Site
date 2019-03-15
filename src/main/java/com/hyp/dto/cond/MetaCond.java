package com.hyp.dto.cond;

/**
 * 

* @Title: MetaCond  

* @Description: meta查询条件  

* @author HanYupeng  

* @date 2018-06-15 10:04
 */
public class MetaCond {

    /**
     * meta Name
     */
    private String name;
    /**
     * 类型
     */
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
