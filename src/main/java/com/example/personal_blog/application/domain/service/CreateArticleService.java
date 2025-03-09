package com.example.personal_blog.application.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.in.CreateArticleCommand;
import com.example.personal_blog.application.port.in.CreateArticleUseCase;
import com.example.personal_blog.application.port.out.CreateArticlePort;
import com.example.personal_blog.application.port.out.LoadArticlePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateArticleService implements CreateArticleUseCase {

    private final LoadArticlePort loadArticlePort;
    private final CreateArticlePort createArticlePort;

    @Override
    public void createArticle(CreateArticleCommand command) {
        List<Article> articles = loadArticlePort.findAll();

        Article article = new Article(
                articles.size() + 1,
                command.title(),
                command.writeDate(),
                command.content());

        createArticlePort.createArticle(article);
    }
}
