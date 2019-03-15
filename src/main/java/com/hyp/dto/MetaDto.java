package com.hyp.dto;

import com.hyp.model.MetaDomain;

/**
 * 

* @Title: MetaDto  

* @Description: 标签、分类列表  

* @author HanYupeng  

* @date 2018-06-15 09:56
 */
public class MetaDto extends MetaDomain {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
