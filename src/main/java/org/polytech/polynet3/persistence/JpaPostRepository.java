package org.polytech.polynet3.persistence;

import org.polytech.polynet3.buisness.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaPostRepository implements PostRepository {

    EntityManager entityManager;


    @Autowired
    public JpaPostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Post save(Post post) {
        entityManager.persist(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public Post findById(Long id) {
        return entityManager.find(Post.class,id);
    }
}
