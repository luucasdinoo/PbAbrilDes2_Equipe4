package br.com.backend.equipe4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "posts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "comments")
    @OneToMany
    private List<Comments> comments = new ArrayList<>();

    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;

    @Column(name = "likes")
    private int likes;

    @Column(name = "retweets")
    private int retweets;

    @Column(name = "number_comments")
    private int numberComments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;


}
