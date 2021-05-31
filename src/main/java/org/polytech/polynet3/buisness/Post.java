package org.polytech.polynet3.buisness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private Long id;

    private String story;

    private LocalDateTime crateAt;

    private long userId;

    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String story, LocalDateTime crateAt, long userId) {
        this.story = story;
        this.crateAt = crateAt;
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(LocalDateTime crateAt) {
        this.crateAt = crateAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
