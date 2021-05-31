package org.polytech.polynet3.buisness;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_post")
public class Post {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "story")
    private String story;

    @Column(name = "created_at")
    private LocalDateTime crateAt;

    @Column(name = "user_id")
    private long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
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
