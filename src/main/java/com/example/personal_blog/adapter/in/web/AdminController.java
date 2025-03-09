package com.example.personal_blog.adapter.in.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.personal_blog.adapter.in.web.form.ArticleCreateForm;
import com.example.personal_blog.application.port.in.CreateArticleCommand;
import com.example.personal_blog.application.port.in.CreateArticleUseCase;
import com.example.personal_blog.application.port.in.GetArticleUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GetArticleUseCase getArticleUseCase;
    private final CreateArticleUseCase createArticleUseCase;

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

    @PostMapping("/new")
    public String createArticle(@Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }

        CreateArticleCommand command = new CreateArticleCommand(
                articleCreateForm.title(),
                LocalDate.parse(articleCreateForm.writeDate()),
                articleCreateForm.content());

        createArticleUseCase.createArticle(command);
        return "redirect:/admin";
    }
}
