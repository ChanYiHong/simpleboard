package HCY.simpleboard.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostTest {

    @Test
    public void PostBuilder() throws Exception {
        //given
        Post post = Post.builder()
                .title("Eunah")
                .author("ChanYi")
                .content("hello, world")
                .build();
        //when

        //then
        assertThat(post.getTitle()).isEqualTo("Eunah");
        assertThat(post.getAuthor()).isEqualTo("ChanYi");
        assertThat(post.getContent()).isEqualTo("hello, world");
    }
}