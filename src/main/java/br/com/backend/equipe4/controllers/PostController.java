package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.dto.*;
import br.com.backend.equipe4.dto.mapper.CommentMapper;
import br.com.backend.equipe4.dto.mapper.PostMapper;
import br.com.backend.equipe4.dto.mapper.RepostMapper;
import br.com.backend.equipe4.entity.Comments;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.exception.GlobalExceptionHandler;
import br.com.backend.equipe4.jwt.JwtUserDetails;
import br.com.backend.equipe4.services.PostService;
import br.com.backend.equipe4.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;


    @Operation(summary = "Make a post", description = "Method to make a post",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Post made successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostCreateDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "422", description = "Invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostCreateDto createDto, @AuthenticationPrincipal JwtUserDetails userDetails){
         Post post = PostMapper.toPost(createDto);
         User user = userService.getUserByUsername(userDetails.getUsername());
         Post createdPost = postService.createPost(post, user);
         return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toDto(createdPost));
    }

    @Operation(summary = "Find a post", description = "Method to find a post by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Post retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "404", description = "Post not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "422", description = "Invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id){
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(PostMapper.toDto(post));
    }


    @Operation(summary = "Update a post", description = "Method to update a post by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Post updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "404", description = "Post not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "422", description = "Invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody PostCreateDto updateDto, @PathVariable Long id){
        postService.updatePost(id, updateDto);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Get home posts", description = "Method to get posts for the home page",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Posts retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDto.class))),
                    @ApiResponse(responseCode = "204", description = "No content available"),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/home")
    public ResponseEntity<List<HomeResponseDto>> home(){
        List<Post> posts =userService.homePage();
        List<HomeResponseDto> homeResponseDtos = new ArrayList<>();
        for( Post post : posts ){
            homeResponseDtos.add(PostMapper.toHome(post));
        }

        return ResponseEntity.ok().body(homeResponseDtos);

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping( value = "/{id}/repost")


    public ResponseEntity<RepostCreateDto> createRepost(@PathVariable Long id, @AuthenticationPrincipal JwtUserDetails user){

        Post rePost = postService.getPostById(id);
        User userRepost = userService.getUserById(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(RepostMapper.toDto(postService.repost(rePost, userRepost)));

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping( value = "/{id}/comment")
    public ResponseEntity<CommentResponseDto> comentPost(@PathVariable Long id, @RequestBody @Valid CommnetCreateDto createcommentDto, @AuthenticationPrincipal JwtUserDetails userDetails){

      Comments comments =  postService.postComment(CommentMapper.toComment(createcommentDto),userService.getUserById(userDetails.getId()),postService.getPostById(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentMapper.toCommnetCreateDto(comments));
    }

}
