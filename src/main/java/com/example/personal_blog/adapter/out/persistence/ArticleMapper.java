package com.example.personal_blog.adapter.out.persistence;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.personal_blog.application.domain.model.Article;

@Component
public class ArticleMapper {

    public ArticleJsonEntity toArticleJsonEntity(Article article) {
        return new ArticleJsonEntity(
                article.title(),
                article.writeDate().toString(),
                article.content());
    }

    public Article toArticle(ArticleJsonEntity articleJsonEntity) {
        return new Article(
                articleJsonEntity.title(),
                LocalDate.parse(articleJsonEntity.writeDate()),
                articleJsonEntity.content());
    }
}
