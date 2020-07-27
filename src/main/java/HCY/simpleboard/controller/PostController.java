package HCY.simpleboard.controller;

import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String createPost(Model model) {
        model.addAttribute("post", new PostSaveRequestDto());
        return "/posts/post_create";
    }
}
