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

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;


    @GetMapping(value = "/post")
    public String createPost(Model model, @LoginUser SessionUser sessionUser) {
        /** User 넘겨봤자 다시 null로 날라오니까 애초에 author만 넘기자. **/
        PostSaveRequestDto requestDto = new PostSaveRequestDto();

        String name = sessionUser.getName();
        requestDto.setAuthor(name);

        model.addAttribute("post", requestDto);
        return "/posts/post_create";
    }

    @PostMapping(value = "/post")
    public String savePost(@Valid PostSaveRequestDto response, BindingResult result, @LoginUser SessionUser sessionUser){
        if(result.hasErrors()) {
            return "/posts/post_create";
        }
        System.out.println(response.getTitle());

        response.setAuthor(sessionUser.getName());

        System.out.println(response.getAuthor());
        System.out.println(response.getContent());


        User user = userService.findUserByName(response.getAuthor());

        System.out.println(user);

        postService.savePost(response, user);
        return "redirect:/";
    }

    @GetMapping(value = "/post/update/{id}")
    public String updatePostView(@PathVariable("id") Long id, Model model){

        PostResponseDto dto = postService.findPostById(id);

        model.addAttribute("post", dto);

        System.out.println("GET id : " + id);

        return "/posts/post_update";
    }

    @PostMapping(value = "/post/update/{id}")
    public String updatePost(@PathVariable Long id, @Valid @ModelAttribute("post") PostUpdateRequestDto response, BindingResult result) {

        if(result.hasErrors()){
            throw new IllegalArgumentException("수정 실패...");
        }
        postService.updatePost(response, id);

        System.out.println("POST id : " + id);
        System.out.println("Title : " + response.getTitle());
        System.out.println("Content : " + response.getContent());

        return "redirect:/";
    }

    @PostMapping(value = "/post/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println(id);
        postService.deletePost(id);
        return "redirect:/";
    }
}
