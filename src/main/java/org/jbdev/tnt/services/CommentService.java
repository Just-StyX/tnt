package org.jbdev.tnt.services;

import org.jbdev.tnt.Repositories.CommentDao;
import org.jbdev.tnt.entities.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void createComment(Comment comment, int articleID) {
        commentDao.save(comment, articleID);
    }

    public List<Comment> allComments(int articleID) {
        return commentDao.findAllComment(articleID);
    }

    public List<Comment> getCommentsByUser(int articleID) {
        return commentDao.getCommentsByUser(articleID);
    }
    public void deleteComment(int commentID) {commentDao.delete(commentID); }
}
