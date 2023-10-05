package org.jbdev.tnt.controllers;

import org.jbdev.tnt.entities.Article;
import org.jbdev.tnt.services.ArticleService;
import org.jbdev.tnt.services.CommentService;
import org.jbdev.tnt.services.LoggedUserManagementService;
import org.jbdev.tnt.services.UserServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserServices userServices;
    private final LoggedUserManagementService loggedUserManagementService;

    public ArticleController(ArticleService articleService,
                             CommentService commentService, UserServices userServices,
                             LoggedUserManagementService loggedUserManagementService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userServices = userServices;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/article")
    public String create() {
        return "createArticle";
    }
    @PostMapping("/article")
    @PreAuthorize("hasAuthority('admin')")
    public String postArticle(Article article) {
        articleService.save(article);

        return "redirect:/article";
    }

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String getMain(Model model) {
        var articles = articleService.getAll();
        model.addAttribute("articles", articles);
        return "main";
    }

    @GetMapping("/home")
    public String getArticle(Model model, Principal principal) {
        var article = articleService.read();
        model.addAttribute("art", article);
        loggedUserManagementService.setEmail(principal.getName());
        loggedUserManagementService.setArticleID(article.getArticleID());
        var user = userServices.userProfile();
        loggedUserManagementService.setLastName(user.getLastName());
        loggedUserManagementService.setFullName(user.getLastName() + user.getFirstName());
        model.addAttribute("principal", user.getLastName());
        return "home";
    }

    @GetMapping("/article/{id}")
    public String getMainArticle(Model model, @PathVariable int id) {
        var article = articleService.read();
        var comment = commentService.getCommentsByUser(id);
        model.addAttribute("comment", comment);
        model.addAttribute("art", article);
        model.addAttribute("principal", loggedUserManagementService.getLastName());
        return "mainarticle";
    }

    @GetMapping("/article/all")
    public String getAll(Model model) {
        var articles = articleService.getAll();
        model.addAttribute("articles", articles);
        return "allArticles";
    }

    @GetMapping("/individual/{articleID}")
    public String getIndividual(Model model, @PathVariable int articleID) {
        var individual = articleService.findOne(articleID);
        var comment = commentService.getCommentsByUser(articleID);
        var admin = userServices.findAdmin();
        model.addAttribute("comment", comment);
        model.addAttribute("art", individual);
        int r = (admin.getEmail().equals(loggedUserManagementService.getEmail())) ? 1 : -1;
        model.addAttribute("owner", r);
        return "individualArticles";
    }

    @GetMapping("/article-edit/{articleID}")
    @PreAuthorize("hasAuthority('admin')")
    public String getUpdate(Model model, @PathVariable int articleID) {
        var edit = articleService.findOne(articleID);
        model.addAttribute("art", edit);
        return "editArticle";
    }

    @PostMapping("/article-edit/{articleID}")
    @PreAuthorize("hasAuthority('admin')")
    public String updateArticle(
            Article article,
            @PathVariable int articleID
    ) {
        articleService.update(articleID, article);

        return "redirect:/article";
    }

    @GetMapping("/article-delete/{articleID}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteArticle(@PathVariable int articleID) {
        articleService.delete(articleID);
        return "redirect:/article";
    }
}
