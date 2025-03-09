package com.example.personal_blog.application.port.in;

import java.util.List;

import com.example.personal_blog.application.domain.model.Article;

public interface GetArticleUseCase {
    List<Article> getArticles();
}
