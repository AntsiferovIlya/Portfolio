package News.News.repo;


import News.News.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM POST p WHERE p.topic=?1", nativeQuery = true)
    List<Post> findAllByTopic(String topic);
}
