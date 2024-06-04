package br.com.backend.equipe4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "posts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "comments")
    @OneToMany(mappedBy = "post")

    private List<Comments> comments;

    @JoinColumn(name = "users_id")
    @ManyToOne
    @JsonIgnore
    private User user;

     @ManyToOne
     @JoinColumn(name = "post_id")
     @JsonIgnore
     private Post post;

    @JoinColumn(name = "author_id")
    @ManyToOne
    @JsonIgnore
    private Author author;

    @Column(name = "likes")
    private int likes;

    @Column(name = "retweets")
    private int retweets;

    @Column(name = "number_comments")
    private int numberComments;

    @CreatedDate
    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "update_at")
    private LocalDateTime updatedAt;


}
