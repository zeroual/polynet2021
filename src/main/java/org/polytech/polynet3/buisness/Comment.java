package org.polytech.polynet3.buisness;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_comment")
public class Comment {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime crateAt;

    @Column(name = "user_id")
    private long userId;
    @Column(name = "post_id")
    private long postId;

    public Comment( String comment, LocalDateTime crateAt, long userId, long postId) {
        this.comment = comment;
        this.crateAt = crateAt;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
