package br.com.backend.equipe4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data @AllArgsConstructor @NoArgsConstructor
public class Author implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    public Author(String author, String username) {
        this.author = author;
        this.username = username;
    }
}
