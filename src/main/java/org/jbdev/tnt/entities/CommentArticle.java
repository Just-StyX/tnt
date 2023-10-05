package org.jbdev.tnt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "comment_article")
public class CommentArticle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment_id")
    private int commentID;

    @Column(name = "article_id")
    private int articleID;

    public CommentArticle() {}

    public CommentArticle(int commentID, int articleID) {
        this.commentID = commentID;
        this.articleID = articleID;
    }
}
