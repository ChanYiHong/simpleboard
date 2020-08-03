package HCY.simpleboard.controller;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.dto.post.PostUpdateRequestDto;
import HCY.simpleboard.service.PostService;
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
}
