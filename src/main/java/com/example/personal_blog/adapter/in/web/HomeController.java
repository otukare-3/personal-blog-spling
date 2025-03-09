package com.example.personal_blog.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.personal_blog.application.port.in.GetArticleUseCase;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final GetArticleUseCase getArticleUseCase;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", getArticleUseCase.getArticles());
        return "index";
    }

}
