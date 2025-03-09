package com.example.personal_blog.application.port.in;

import java.time.LocalDate;

public record CreateArticleCommand(
        String title,
        LocalDate writeDate,
        String content) {

}
