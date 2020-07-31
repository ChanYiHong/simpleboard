package HCY.simpleboard.repository;

import HCY.simpleboard.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void SaveRepository() throws Exception {
        //given
        Post post = post생성기();

        //when
        postRepository.save(post);
        Post findPost = postRepository.findById(post.getId());

        //then
        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
    }
    
    @Test
    @Transactional
    public void findRepository() throws Exception {
        //given
        Post post = post생성기();

        postRepository.save(post);
        //when
        Post findPost = postRepository.findById(post.getId());

        //then
        assertThat(findPost.getId()).isEqualTo(post.getId());
    }

    @Test
    @Transactional
    public void findAllRepository() throws Exception {
        //given
        Post post1 = post생성기();
        Post post2 = Post.builder()
                .title("AAA").build();
        Post post3 = Post.builder()
                .title("BBB").build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        //when
        List<Post> posts = postRepository.findAll();

        //then
        assertThat(posts.get(0).getId()).isEqualTo(post1.getId());
        assertThat(posts.get(1).getId()).isEqualTo(post2.getId());
        assertThat(posts.get(2).getId()).isEqualTo(post3.getId());
    }

    private Post post생성기(){
        return Post.builder()
                .title("Eunah")
                .author("ChanYi")
                .content("Hello, world!")
                .build();
    }

}