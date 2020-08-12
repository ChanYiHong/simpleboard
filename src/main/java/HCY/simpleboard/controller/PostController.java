package HCY.simpleboard.controller;

import HCY.simpleboard.config.auth.LoginUser;
import HCY.simpleboard.config.auth.dto.SessionUser;
import HCY.simpleboard.domain.Post;
import HCY.simpleboard.domain.user.User;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.dto.post.PostUpdateRequestDto;
import HCY.simpleboard.service.PostService;
import HCY.simpleboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;


    /** Post 저장 (Get) **/
    @GetMapping(value = "/post")
    public String createPost(Model model, @LoginUser SessionUser sessionUser) {
        /** User 넘겨봤자 다시 null로 날라오니까 애초에 author를 저장해서 넘기자. **/
        PostSaveRequestDto requestDto = new PostSaveRequestDto();

        String name = sessionUser.getName();
        requestDto.setAuthor(name);

        model.addAttribute("post", requestDto);
        return "/posts/post_create";
    }

    /** Post 저장 (Post) **/
    @PostMapping(value = "/post")
    public String savePost(@Valid PostSaveRequestDto response, BindingResult result, @LoginUser SessionUser sessionUser){
        if(result.hasErrors()) {
            return "/posts/post_create";
        }
        // form에서 나온 response에 author가 null로 오기 때문에, session user의 이름을 저장해준다.
        response.setAuthor(sessionUser.getName());

        User user = userService.findUserByName(response.getAuthor());

        postService.savePost(response, user);
        return "redirect:/";
    }

    /** Post 수정 (Get) **/
    @GetMapping(value = "/post/update/{id}")
    public String updatePostView(@PathVariable("id") Long id, Model model, @LoginUser SessionUser sessionUser){

        PostResponseDto dto = postService.findPostById(id);

        model.addAttribute("post", dto);

        return "/posts/post_update";
    }

    /** Post 수정 (Post) **/
    @PostMapping(value = "/post/update/{id}")
    public String updatePost(@PathVariable("id") Long id, @Valid @ModelAttribute("post") PostUpdateRequestDto response, BindingResult result) {

        if(result.hasErrors()){
            throw new IllegalArgumentException("Fail to Adjust...");
        }
        postService.updatePost(response, id);
        return "redirect:/";
    }

    /** Post 삭제 (Post) **/  // DeleteMapping 을 써야 하는데 .. 그건 잘 모르겠다. 연동이 안되던데 html이랑
    @PostMapping(value = "/post/delete/{id}")
    public String delete(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/";
    }
}
