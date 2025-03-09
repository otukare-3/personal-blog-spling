package com.example.personal_blog.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.personal_blog.adapter.in.web.form.ArticleCreateForm;
import com.example.personal_blog.application.port.in.GetArticleUseCase;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GetArticleUseCase getArticleUseCase;

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("articles", getArticleUseCase.getArticles());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        ArticleCreateForm articleCreateForm = new ArticleCreateForm("", "", "");
        model.addAttribute(articleCreateForm);
        return "admin/new";
    }
}
