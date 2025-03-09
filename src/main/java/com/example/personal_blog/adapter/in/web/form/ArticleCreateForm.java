package com.example.personal_blog.adapter.in.web.form;

import jakarta.validation.constraints.NotEmpty;

public record ArticleCreateForm(
                @NotEmpty String title,
                @NotEmpty String writeDate,
                @NotEmpty String content) {
}
