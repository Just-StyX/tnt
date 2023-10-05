package org.jbdev.tnt.controllers;

import org.jbdev.tnt.entities.Comment;
import org.jbdev.tnt.services.CommentService;
import org.jbdev.tnt.services.LoggedUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public CommentController(CommentService commentService, LoggedUserManagementService loggedUserManagementService) {
        this.commentService = commentService;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @PostMapping("/article/{articleID}/comment")
    public String postComment(Comment comment, @PathVariable int articleID) {
        commentService.createComment(comment, articleID);
        return "redirect:/article/" + articleID;
    }

    @PostMapping("/individual/{articleID}/comment")
    public String postIndividualComment(Comment comment, @PathVariable int articleID) {
        commentService.createComment(comment, articleID);
        return "redirect:/individual/" + articleID;
    }

    @GetMapping("/article/{articleID}/comment")
    public String getAllComments(@PathVariable int articleID, Model model) {
        List<Comment> comments = commentService.allComments(articleID);
        model.addAttribute("comments", comments);
        model.addAttribute("id", articleID);
        return "allComments";
    }

//    @GetMapping("/article/{articleID}/user/comment")
//    public List<Comment> getUserComments(@PathVariable int articleID) {
//        return commentService.getCommentsByUser(articleID);
//    }

    @GetMapping("/comment/{commentID}")
    public String deleteComment(@PathVariable int commentID) {
        commentService.deleteComment(commentID);
        return "redirect:/article/" + loggedUserManagementService.getArticleID();
    }
}
