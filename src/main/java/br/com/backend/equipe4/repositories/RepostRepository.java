package br.com.backend.equipe4.repositories;

import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.Repost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepostRepository extends JpaRepository<Repost, Long> {
}

