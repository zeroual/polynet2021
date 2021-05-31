package org.polytech.polynet3.buisness;

import java.util.List;

public interface PostService {
    void publish(String story, long userId);

    List<Post> fetchAll();

    void comment(Comment comment);
}
