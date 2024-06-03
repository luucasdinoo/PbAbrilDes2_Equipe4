package br.com.backend.equipe4.services;

import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.entity.Author;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.Repost;
import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.repositories.PostRepository;
import br.com.backend.equipe4.repositories.RepostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final RepostRepository repostRepository;

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
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setUpdatedAt(LocalDateTime.now());
        modelMapper.map(updateDto, post);
        postRepository.save(post);
    }

    @Transactional
    public Repost repost(Post post, User user) {
        Repost repost = new Repost();
        repost.setPostId(post.getId());
        repost.setAuthorName(post.getAuthor().getUsername());
        repost.setUserId(user.getId());
        post.setRetweets(post.getRetweets() + 1);
        return repostRepository.save(repost);
    }
}
