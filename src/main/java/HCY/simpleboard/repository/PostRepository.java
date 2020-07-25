package HCY.simpleboard.repository;

import HCY.simpleboard.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    EntityManager em;

    public Long save(Post post){
        em.persist(post);
        return post.getId();
    }

    public Post findById(Long id){
        Post post = em.find(Post.class, id);
        return post;
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }
}
