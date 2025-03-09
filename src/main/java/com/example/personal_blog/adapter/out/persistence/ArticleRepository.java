package com.example.personal_blog.adapter.out.persistence;

import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.out.LoadArticlePort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class ArticleRepository implements LoadArticlePort {

    private static final String JSON_FILE_PATH = "data.json";
    private final ArticleMapper articleMapper;
    private final ObjectMapper objectMapper;

    public ArticleRepository(ObjectMapper objectMapper, ArticleMapper articleMapper) {
        this.objectMapper = objectMapper;
        this.articleMapper = articleMapper;
    }

    public List<Article> findAll() {
        try {

            byte[] jsonData = Files.readAllBytes(Paths.get(JSON_FILE_PATH));

            List<ArticleJsonEntity> articleJsonEntities = objectMapper.readValue(jsonData,
                    new TypeReference<List<ArticleJsonEntity>>() {
                    });

            return articleJsonEntities.stream()
                    .map(entity -> articleMapper.toArticle(entity))
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Error reading data.json file", e);
        }

    }
}