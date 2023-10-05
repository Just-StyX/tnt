package org.jbdev.tnt.Repositories;

import org.jbdev.tnt.entities.Comment;

import java.util.List;

public interface CommentDao {
    void save(Comment comment, int articleID);
    List<Comment> findAllComment(int articleID);
    List<Comment> getCommentsByUser(int articleID);
    void delete(int commentID);
}
