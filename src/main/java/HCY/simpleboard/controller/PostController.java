package HCY.simpleboard.controller;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/post")
    public String createPost(Model model) {
        model.addAttribute("post", new PostSaveRequestDto());
        return "/posts/post_create";
    }

    @PostMapping(value = "/post")
    public String savePost(@Valid PostSaveRequestDto response, BindingResult result){
        if(result.hasErrors()) {
            return "/posts/post_create";
        }
        postService.savePost(response);
        return "redirect:/";
    }
}
