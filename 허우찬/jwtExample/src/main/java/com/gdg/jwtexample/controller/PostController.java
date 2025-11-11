package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.post.PostCreateReq;
import com.gdg.jwtexample.dto.post.PostInfoRes;
import com.gdg.jwtexample.dto.post.PostUpdateReq;
import com.gdg.jwtexample.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostInfoRes> createPost(Principal principal, @RequestBody PostCreateReq postCreateReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(principal, postCreateReq));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoRes> getPost(Principal principal, @PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostInfo(principal, postId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostInfoRes> updatePost(Principal principal, @PathVariable Long postId, @RequestBody PostUpdateReq postUpdateReq) {
        return ResponseEntity.ok(postService.updatePost(principal, postId, postUpdateReq));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(Principal principal, @PathVariable Long postId) {
        postService.deletePost(principal, postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<PostInfoRes>> getAllMyPosts(Principal principal) {
        return ResponseEntity.ok(postService.getAllMyPosts(principal));
    }
}
