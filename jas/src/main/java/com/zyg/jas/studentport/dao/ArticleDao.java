package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Article;

public interface ArticleDao {
    Integer publishArticle(Article article);//发表一篇文章，在数据库里增加一条数据
    Integer deleteArticleById(Integer id);//根据文章id删除一篇文章

}
