package br.com.backend.equipe4.services;

import br.com.backend.equipe4.entity.Author;
import br.com.backend.equipe4.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
