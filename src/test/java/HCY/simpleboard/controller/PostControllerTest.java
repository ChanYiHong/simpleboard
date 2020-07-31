package HCY.simpleboard.controller;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.repository.PostRepository;
import HCY.simpleboard.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class PostControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PostRepository postRepository;

    private MockMvc mockMvc;

    @Before
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void post생성화면() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/post"))
                .andExpect(status().isOk());
    }


    @Test
    public void post생성완료_및_저장() throws Exception {
        //given
        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "생일")
                .param("author", "찬의")
                .param("content", "생일축하해"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        //when
        Post post = Post.builder()
                .title("생일").author("찬의").content("생일축하해").build();

        PostResponseDto expectedPost = new PostResponseDto(post);

        //then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", hasSize(1)));

    }

//    @Test
//    public void 업데이트() throws Exception {
//        //given
//
//        Post post = Post.builder().content("제목").author("저자").content("내용").build();
//        postRepository.save(post)
//
//        //when
//
//        mockMvc.perform(post("/post")
//                .param("title", "제목")
//                .param("author", "저자")
//                .param("content", "내용"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(header().string("Location", "/"));
//
//        //then
//
//    }

}