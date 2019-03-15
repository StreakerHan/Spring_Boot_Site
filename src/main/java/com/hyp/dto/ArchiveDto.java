package com.hyp.dto;

import java.util.List;

import com.hyp.model.ContentDomain;

/**
 * 

* @Title: ArchiveDto  

* @Description: 文章归档类  

* @author HanYupeng  

* @date 2018-06-15 09:55
 */
public class ArchiveDto {

    private String date;
    private String count;
    private List<ContentDomain> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ContentDomain> getArticles() {
        return articles;
    }

    public void setArticles(List<ContentDomain> articles) {
        this.articles = articles;
    }
}
