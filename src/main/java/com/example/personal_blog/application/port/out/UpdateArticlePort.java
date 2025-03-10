package com.example.personal_blog.application.port.out;

import com.example.personal_blog.application.domain.model.Article;

public interface UpdateArticlePort {
    void updateArticle(Article article);
}
