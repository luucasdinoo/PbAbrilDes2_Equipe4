package br.com.backend.equipe4.services;

import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.entity.Author;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final AuthorService authorService;

    @Transactional
    public Post createPost(Post post, User user) {

        Author author = authorService.getAuthorByUsername(user.getUsername());
        if (author == null)
            author = authorService.saveAuthor(new Author(user.getFirstName() + " " + user.getLastName(), user.getUsername()));

        post.setUser(user);
        post.setAuthor(author);
        post.setLikes(0);
        post.setNumberComments(0);
        post.setRetweets(0);
        post.setCreatedAt(LocalDateTime.now());
        author.getPosts().add(post);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void updatePost(Long id, PostCreateDto updateDto) {

        Post exitingPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!exitingPost.getAuthor().getUsername().equals(currentUsername))
            throw new AccessDeniedException("You are not authorized to update this post");

        exitingPost.setUpdatedAt(LocalDateTime.now());
        modelMapper.map(updateDto, exitingPost);
        postRepository.save(exitingPost);
    }
}
