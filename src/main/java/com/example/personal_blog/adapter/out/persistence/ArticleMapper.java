package com.example.personal_blog.adapter.out.persistence;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.personal_blog.application.domain.model.Article;

/**
 * Article と ArticleJsonEntity の間の変換を行うマッパークラス。
 */
@Component
public class ArticleMapper {

    /**
     * Article オブジェクトを ArticleJsonEntity オブジェクトに変換します。
     *
     * @param article 変換する Article オブジェクト
     * @return 変換された ArticleJsonEntity オブジェクト
     */
    public ArticleJsonEntity toArticleJsonEntity(Article article) {
        return new ArticleJsonEntity(
                Integer.toString(article.id()),
                article.title(),
                article.writeDate().toString(),
                article.content());
    }

    /**
     * ArticleJsonEntity オブジェクトを Article オブジェクトに変換します。
     *
     * @param articleJsonEntity 変換する ArticleJsonEntity オブジェクト
     * @return 変換された Article オブジェクト
     */
    public Article toArticle(ArticleJsonEntity articleJsonEntity) {
        return new Article(
                Integer.parseInt(articleJsonEntity.id()),
                articleJsonEntity.title(),
                LocalDate.parse(articleJsonEntity.writeDate()),
                articleJsonEntity.content());
    }
}
