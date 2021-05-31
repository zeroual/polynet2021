package org.polytech.polynet3.persistence;

import org.polytech.polynet3.buisness.Post;

import java.util.List;

public interface    PostRepository {

    Post save(Post post);
    List<Post> findAll();

    Post findById(Long id);
}
