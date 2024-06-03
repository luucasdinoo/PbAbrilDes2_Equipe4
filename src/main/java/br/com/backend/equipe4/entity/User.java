package br.com.backend.equipe4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class) // Processo de auditoria
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_USER;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @CreatedBy
    @Column(name = "created_at")
    private String createdAt;

    @LastModifiedBy
    @Column(name = "update_at")
    private String updatedAt;

    public enum Role{
        ROLE_USER
    }
}