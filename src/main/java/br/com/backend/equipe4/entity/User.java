package br.com.backend.equipe4.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "summary", nullable = true, length = 300)
    private String summary;

    @Column(name = "birthdate", nullable = false, length = 10)
    private LocalDateTime dateOfBirth;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;
}
