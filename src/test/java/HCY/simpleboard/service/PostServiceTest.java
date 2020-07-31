package HCY.simpleboard.service;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.dto.post.PostUpdateRequestDto;
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
        PostSaveRequestDto saveRequestDto  = PostSaveRequestDto.builder()
                .title("Eunah").author("ChanYi").content("contents").build();

        //when

        Long findId = postService.savePost(saveRequestDto);

        //then

        PostResponseDto findPost = postService.findPostById(findId);

        assertThat(findPost.getTitle()).isEqualTo(saveRequestDto.getTitle());
        assertThat(findPost.getAuthor()).isEqualTo(saveRequestDto.getAuthor());
        assertThat(findPost.getContent()).isEqualTo(saveRequestDto.getContent());
    }


    @Test
    public void updatePostDto() throws Exception {
        //given

        PostSaveRequestDto post = PostSaveRequestDto.builder().content("제목").author("저자").content("내용").build();
        Long id = postService.savePost(post);

        //when

//        PostUpdateRequestDto updateRequestDto = PostUpdateRequestDto.builder().title("다른제목").content("다른내용").build();
//
//        Long updatedId = postService.updatePost(updateRequestDto, id);
//
//        Post updatedPost = postService.findPost(updatedId);
//
//        //then
//
//        assertThat(updatedPost.getTitle()).isEqualTo(updateRequestDto.getTitle());
//        assertThat(updatedPost.getContent()).isEqualTo(updateRequestDto.getContent());
    }
}