package com.example.personal_blog.application.domain.service;

import org.springframework.stereotype.Service;

import com.example.personal_blog.application.port.in.DeleteArticleUseCase;
import com.example.personal_blog.application.port.out.DeleteArticlePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;

    @Override
    public void deleteArticle(int id) {
        deleteArticlePort.deleteArticle(id);
    }

}
