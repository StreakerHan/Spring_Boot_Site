package com.hyp.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.hyp.model.MetaDomain;

/**
 * 

* @Title: AdminCommons  

* @Description: 后台公共函数  

* @author HanYupeng  

* @date 2018-06-15 10:35
 */
@Component
public final class AdminCommons {

    /**
     * 判断category和cat的交集
     *
     * @param cats
     * @return
     */
    public static boolean exist_cat(MetaDomain category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    public static String rand_color() {
        int r = Tools.rand(0, COLORS.length - 1);
        return COLORS[r];
    }

}
