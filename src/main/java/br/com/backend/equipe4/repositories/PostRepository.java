package br.com.backend.equipe4.repositories;

import br.com.backend.equipe4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository  extends JpaRepository<Post, Long> {
}
