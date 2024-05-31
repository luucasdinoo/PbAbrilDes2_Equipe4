package br.com.backend.equipe4.services;

import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.repositories.PostRepository;
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

    @Transactional
    public Post createPost(Post post) {
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
}
