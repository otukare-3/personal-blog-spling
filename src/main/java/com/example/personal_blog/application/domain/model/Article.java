package com.example.personal_blog.application.domain.model;

import java.time.LocalDate;

public record Article(
        String title,
        LocalDate writeDate,
        String content) {
}
