package com.example.personal_blog.adapter.out.persistence;

import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.out.CreateArticlePort;
import com.example.personal_blog.application.port.out.LoadArticlePort;
import com.example.personal_blog.application.port.out.UpdateArticlePort;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * 記事データを読み込むためのリポジトリクラス。
 */
@Repository
public class ArticleRepository implements LoadArticlePort, CreateArticlePort, UpdateArticlePort {

    private static final String JSON_FILE_PATH = "data.json";
    private final ArticleMapper articleMapper;
    private final ObjectMapper objectMapper;

    /**
     * ArticleRepositoryのコンストラクタ。
     *
     * @param objectMapper  JSONデータをマッピングするためのObjectMapper
     * @param articleMapper ArticleJsonEntityをArticleに変換するためのArticleMapper
     */
    public ArticleRepository(ObjectMapper objectMapper, ArticleMapper articleMapper) {
        this.objectMapper = objectMapper;
        this.articleMapper = articleMapper;
    }

    /**
     * data.jsonファイルからすべての記事を読み込み、Articleオブジェクトのリストを返します。
     *
     * @return 記事のリスト
     * @throws RuntimeException data.jsonファイルの読み込み中にエラーが発生した場合
     */
    public List<Article> findAll() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(JSON_FILE_PATH));

            List<ArticleJsonEntity> articleJsonEntities = objectMapper.readValue(jsonData,
                    new TypeReference<List<ArticleJsonEntity>>() {
                    });

            return articleJsonEntities.stream()
                    .map(articleMapper::toArticle)
                    .sorted((a1, a2) -> Integer.compare(a1.id(), a2.id()))
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Error reading data.json file", e);
        }
    }

    @Override
    public Article findById(int id) {
        return findAll().stream()
                .filter(article -> article.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @Override
    public void createArticle(Article article) {
        try {
            List<Article> articles = findAll();

            List<Article> newArticles = Stream.concat(articles.stream(), Stream.of(article))
                    .toList();

            writeArticleFile(newArticles);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data.json file", e);
        }
    }

    @Override
    public void updateArticle(Article article) {
        try {
            List<Article> articles = findAll().stream()
                    .filter(a -> article.id() != a.id())
                    .toList();

            List<Article> newArticles = Stream.concat(articles.stream(), Stream.of(article))
                    .toList();

            writeArticleFile(newArticles);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data.json file", e);
        }
    }

    private void writeArticleFile(List<Article> target)
            throws IOException, StreamWriteException, DatabindException {
        List<ArticleJsonEntity> articleJsonEntities = target.stream()
                .map(articleMapper::toArticleJsonEntity)
                .toList();

        objectMapper.writeValue(
                Paths.get(JSON_FILE_PATH).toFile(),
                articleJsonEntities);
    }
}