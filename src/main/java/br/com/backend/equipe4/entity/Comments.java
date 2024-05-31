package br.com.backend.equipe4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity()
@Data @NoArgsConstructor @AllArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_comment")
    private LocalDateTime date;

    @Column(name = "likes")
    private int likes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;

}
