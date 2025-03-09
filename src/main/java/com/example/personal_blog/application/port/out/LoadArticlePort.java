package com.example.personal_blog.application.port.out;

import java.util.List;

import com.example.personal_blog.application.domain.model.Article;

public interface LoadArticlePort {
    List<Article> findAll();

    Article findById(int id);
}
