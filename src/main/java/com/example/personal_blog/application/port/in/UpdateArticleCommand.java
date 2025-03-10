package com.example.personal_blog.application.port.in;

import java.time.LocalDate;

public record UpdateArticleCommand(
        int id,
        String title,
        LocalDate writeDate,
        String content) {

}
