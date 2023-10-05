package org.jbdev.tnt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @Column(name = "author")
    private String author;

    @Column(name = "comments")
    private String comment;

    @Column(name = "article_id")
    private int articleID;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    public Comment() {}

    public Comment(String comment) {this.comment = comment;}
    public Comment(String comment, String author) {this.comment = comment; this.author = author;}

    public Comment(String author, String comment, int articleID) {
        this.author = author;
        this.comment = comment;
        this.dateCreated = LocalDate.now();
        this.articleID = articleID;
    }

}
