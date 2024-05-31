package br.com.backend.equipe4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "users")
@Getter @Setter @ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "summary", nullable = true, length = 300)
    private String summary;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate", nullable = false, length = 10)
    private LocalDate birthdate;

    public boolean isValidAge() {
        LocalDate now = LocalDate.now();
        LocalDate eighteenYearsAgo = now.minusYears(18);
        return birthdate.isBefore(eighteenYearsAgo);
    }

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
