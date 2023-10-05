package org.jbdev.tnt.Repositories;

import jakarta.persistence.EntityManager;
import org.jbdev.tnt.entities.Comment;
import org.jbdev.tnt.services.LoggedUserManagementService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{
    private final EntityManager entityManager;
    private final LoggedUserManagementService loggedUserManagementService;

    public CommentDaoImpl(EntityManager entityManager, LoggedUserManagementService loggedUserManagementService) {
        this.entityManager = entityManager;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @Override
    @Transactional
    public void save(Comment comment, int articleID) {
        String author = loggedUserManagementService.getEmail();
        String comm = comment.getComment();
        Comment comment1 = new Comment(author, comm, articleID);
        entityManager.persist(comment1);
    }

    @Override
    public List<Comment> findAllComment(int articleID) {
        List<Comment> comments = find();
        List<Comment> result = new ArrayList<>();
        for(Comment comment: comments) {
            if(comment.getArticleID() == articleID) {
                result.add(comment);
            }
        }
        return result;
    }

    @Override
    public List<Comment> getCommentsByUser(int articleID) {
        String author = loggedUserManagementService.getEmail();
        List<Comment> comments = find();
        List<Comment> result = new ArrayList<>();
        for(Comment comment: comments) {
            if((comment.getArticleID() == articleID) && (comment.getAuthor().equals(author))) {
                result.add(comment);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void delete(int commentID) {
        Comment comment = entityManager.find(Comment.class, commentID);
        entityManager.remove(comment);
    }

    public List<Comment> find() {
        String query = "from Comment";
        return entityManager.createQuery(query, Comment.class).getResultList();
    }
}
