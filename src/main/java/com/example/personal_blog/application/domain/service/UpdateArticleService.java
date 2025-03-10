package com.example.personal_blog.application.domain.service;

import org.springframework.stereotype.Service;

import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.in.UpdateArticleCommand;
import com.example.personal_blog.application.port.in.UpdateArticleUseCase;
import com.example.personal_blog.application.port.out.LoadArticlePort;
import com.example.personal_blog.application.port.out.UpdateArticlePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateArticleService implements UpdateArticleUseCase {

    private final UpdateArticlePort updateArticlePort;
    private final LoadArticlePort loadArticlePort;

    @Override
    public void updateArticle(UpdateArticleCommand command) {

        Article article = loadArticlePort.findById(command.id());

        Article newArticle = new Article(
                article.id(),
                command.title(),
                command.writeDate(),
                command.content());

        updateArticlePort.updateArticle(newArticle);
    }

}
