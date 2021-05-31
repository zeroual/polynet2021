package org.polytech.polynet3.persistence;

import org.polytech.polynet3.buisness.Comment;
import org.polytech.polynet3.buisness.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcPostRepository implements PostRepository {


    private DataSource dataSource;

    @Autowired
    public JdbcPostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Post save(Post post) {
        // INSERT POST

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement postStatement = connection.prepareStatement("replace into T_POST(id,story ,created_at ,user_id) VALUES (?,?,?,?)");
            postStatement.setLong(1, post.getId());
            postStatement.setString(2, post.getStory());
            postStatement.setTimestamp(3, Timestamp.valueOf(post.getCrateAt()));
            postStatement.setLong(4, post.getUserId());
            postStatement.executeUpdate();
            ResultSet rs = postStatement.executeQuery("SELECT max(id) from T_POST");
            long generatedPosId = 0;
            if (rs.next()) {
                generatedPosId = rs.getLong(1);
            }
            // INSERT comments


            long finalGeneratedPosId = generatedPosId;
            post.getComments().forEach(comment -> {
                try {
                    PreparedStatement commentStatement = connection.prepareStatement("insert into T_COMMENT(comment ,created_at ,user_id ,post_id) VALUES (?,?,?,?)");
                    commentStatement.setString(1, comment.getComment());
                    commentStatement.setTimestamp(2, Timestamp.valueOf(comment.getCrateAt()));
                    commentStatement.setLong(3, post.getUserId());
                    commentStatement.setLong(4, finalGeneratedPosId);
                    commentStatement.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            connection.close();


            // INSERT Likes

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement postStatement = connection.createStatement();
            ResultSet rs = postStatement.executeQuery("SELECT * FROM T_POST");
            while (rs.next()) {
                posts.add(mapToPost(rs));
            }
            connection.close();
        } catch (Exception e) {

        }

        return posts;
    }

    @Override
    public Post findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement postStatement = connection.prepareStatement("SELECT * FROM T_POST WHERE  id=?");
            postStatement.setLong(1, id);
            ResultSet rs = postStatement.executeQuery();
            if (rs.next()) {
                return mapToPost(rs);
            }
            connection.close();

            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Post mapToPost(ResultSet rs) throws SQLException {
        Connection connection = dataSource.getConnection();
        Post post = new Post(rs.getString("story"), rs.getTimestamp("created" +
                "_at").toLocalDateTime(), rs.getLong("user_id"));
        post.setId(rs.getLong("id"));
        PreparedStatement commentStatement = connection.prepareStatement("SELECT * FROM T_COMMENT WHERE  post_id=?");
        commentStatement.setLong(1, rs.getLong("id"));
        ResultSet resultSet = commentStatement.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment(resultSet.getString("comment"), rs.getTimestamp("created" +
                    "_at").toLocalDateTime(), resultSet.getLong("user_id"), rs.getLong("id"));
            comment.setId(resultSet.getLong("id"));
            post.getComments().add(comment);
        }
        connection.close();

        return post;
    }
}
