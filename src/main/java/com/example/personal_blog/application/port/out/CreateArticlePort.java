package com.example.personal_blog.application.port.out;

import com.example.personal_blog.application.domain.model.Article;

public interface CreateArticlePort {
    void createArticle(Article article);
}
