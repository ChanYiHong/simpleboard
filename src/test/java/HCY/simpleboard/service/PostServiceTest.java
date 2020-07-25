package HCY.simpleboard.service;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    public void savePostDto() throws Exception {
        //given
        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title("Eunah").author("ChanYi").content("contents").build();

        //when

        Long findId = postService.savePost(requestDto);

        //then

        Post post = postRepository.findById(findId);

        assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(post.getAuthor()).isEqualTo(requestDto.getAuthor());
        assertThat(post.getContent()).isEqualTo(requestDto.getContent());
    }
}