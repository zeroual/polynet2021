package org.polytech.polynet3.buisness;

import org.polytech.polynet3.persistence.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void publish(String story, long userId) {
        postRepository.save(new Post(story, LocalDateTime.now(),userId));
    }

    @Override
    public List<Post> fetchAll() {
        return postRepository.findAll();
    }

    @Override
    public void comment(Comment comment) {
        Post post = postRepository.findById(comment.getPostId());
        if (post != null) {
            post.getComments().add(comment);
            postRepository.save(post);
        }
    }
}
