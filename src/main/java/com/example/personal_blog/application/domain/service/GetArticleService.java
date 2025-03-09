package com.example.personal_blog.application.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.in.GetArticleUseCase;
import com.example.personal_blog.application.port.out.LoadArticlePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetArticleService implements GetArticleUseCase {

    private final LoadArticlePort loadArticlePort;

    @Override
    public List<Article> getArticles() {
        return loadArticlePort.findAll();
    }

    @Override
    public Article getArticle(int id) {
        return loadArticlePort.findById(id);
    }

}
