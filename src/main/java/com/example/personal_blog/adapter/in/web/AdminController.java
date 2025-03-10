package com.example.personal_blog.adapter.in.web;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.personal_blog.adapter.in.web.form.ArticleCreateForm;
import com.example.personal_blog.application.domain.model.Article;
import com.example.personal_blog.application.port.in.CreateArticleCommand;
import com.example.personal_blog.application.port.in.CreateArticleUseCase;
import com.example.personal_blog.application.port.in.DeleteArticleUseCase;
import com.example.personal_blog.application.port.in.GetArticleUseCase;
import com.example.personal_blog.application.port.in.UpdateArticleCommand;
import com.example.personal_blog.application.port.in.UpdateArticleUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GetArticleUseCase getArticleUseCase;
    private final CreateArticleUseCase createArticleUseCase;
    private final UpdateArticleUseCase updateArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

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

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable int id, Model model) {
        Article article = getArticleUseCase.getArticle(id);
        ArticleCreateForm articleCreateForm = new ArticleCreateForm(
                article.title(),
                article.writeDate().toString(),
                article.content());
        model.addAttribute("id", id);
        model.addAttribute(articleCreateForm);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateArticle(@PathVariable int id, @Valid ArticleCreateForm articleCreateForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }

        UpdateArticleCommand command = new UpdateArticleCommand(
                id,
                articleCreateForm.title(),
                LocalDate.parse(articleCreateForm.writeDate()),
                articleCreateForm.content());

        updateArticleUseCase.updateArticle(command);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public HttpHeaders deleteArticle(@PathVariable int id) {
        deleteArticleUseCase.deleteArticle(id);
        return new HttpHeaders();
    }
}
