package br.com.backend.equipe4.repositories;

import br.com.backend.equipe4.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByUsername(String username);
}
