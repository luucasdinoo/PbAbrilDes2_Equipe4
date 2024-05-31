package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.dto.PostResponseDto;
import br.com.backend.equipe4.dto.mapper.PostMapper;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.jwt.JwtUserDetails;
import br.com.backend.equipe4.services.PostService;
import br.com.backend.equipe4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    // @PreAuthorize()
    @GetMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateDto createDto, @AuthenticationPrincipal JwtUserDetails userDetails){
        Post post = postService.createPost(PostMapper.toPost(createDto));
         post.setAuthor(PostMapper.toAuthor(userService.getUserByUsername(userDetails.getUsername())));
         return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toDto(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id){
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(PostMapper.toDto(post));
    }

    @PatchMapping()
    public ResponseEntity<Void> updatePost(){
        return null;
        //TODO
    }

    @GetMapping("/home")
    public ResponseEntity<Void> getPostsHome(){
        return null;
        //TODO
    }
}
