package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Post;
import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.post.PostCreateReq;
import com.gdg.jwtexample.dto.post.PostInfoRes;
import com.gdg.jwtexample.dto.post.PostUpdateReq;
import com.gdg.jwtexample.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public PostInfoRes createPost(Principal principal, PostCreateReq postCreateReq) {
        User user = userService.getUserEntity(Long.parseLong(principal.getName()));
        Post post = postRepository.save(Post.builder()
                .title(postCreateReq.title())
                .content(postCreateReq.content())
                .user(user)
                .build());

        return PostInfoRes.fromEntity(post);
    }

    @Transactional(readOnly = true)
    public PostInfoRes getPostInfo(Long postId) {
        return PostInfoRes.fromEntity(getPost(postId));
    }

    @Transactional
    public PostInfoRes updatePost(Principal principal, Long postId, PostUpdateReq postUpdateReq) {
        Post post = getPost(postId);
        validateAuthor(principal, post);

        post.update(
                postUpdateReq.title() == null ? post.getTitle() : postUpdateReq.title(),
                postUpdateReq.content() == null ? post.getContent() : postUpdateReq.content());
        return PostInfoRes.fromEntity(post);
    }

    @Transactional
    public void deletePost(Principal principal, Long postId) {
        Post post = getPost(postId);
        validateAuthor(principal, post);

        postRepository.delete(post);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));
    }

    private void validateAuthor(Principal principal, Post post) {
        if (!post.getUser().getId().equals(Long.parseLong(principal.getName()))) {
            throw new RuntimeException("해당 게시물에 접근할 권한이 없습니다.");
        }
    }
}
