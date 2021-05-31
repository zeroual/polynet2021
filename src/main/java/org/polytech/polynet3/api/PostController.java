package org.polytech.polynet3.api;

import org.polytech.polynet3.buisness.Comment;
import org.polytech.polynet3.buisness.Post;
import org.polytech.polynet3.buisness.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    void share(@RequestBody String story) {
        // to get from security context
        long userId = 1;
        postService.publish(story, userId);
    }

    @GetMapping("/api/posts")
    List<Post> findAll() {
        return postService.fetchAll();
    }

    @PostMapping("/api/posts/{id}/comments")
    void comment(@PathVariable("id") Long postId, @RequestBody String comment) {
        long userId = 1;
        postService.comment(new Comment(comment, LocalDateTime.now(), userId, postId));
    }
}
