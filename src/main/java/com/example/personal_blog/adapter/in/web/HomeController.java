package com.example.personal_blog.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.personal_blog.application.port.in.GetArticleUseCase;

import lombok.RequiredArgsConstructor;

/**
 * HomeControllerは、ルートURL ("/") へのリクエストを処理するコントローラです。
 * GetArticleUseCaseを使用して記事のリストを取得し、それをモデルに追加します。
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final GetArticleUseCase getArticleUseCase;

    /**
     * ルートURL ("/") へのGETリクエストを処理します。
     * GetArticleUseCaseを使用して記事のリストを取得し、それをモデルに追加します。
     *
     * @param model 記事のリストが追加されるモデル
     * @return レンダリングされるビューの名前 ("index")
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", getArticleUseCase.getArticles());
        return "index";
    }

    @GetMapping("/article/{id}")
    public String detail(
            @PathVariable String id,
            Model model) {
        model.addAttribute("article", getArticleUseCase.getArticle(Integer.parseInt(id)));
        return "detail";
    }

}
