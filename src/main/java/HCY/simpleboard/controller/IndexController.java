package HCY.simpleboard.controller;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping(value = "/")
    public String index(Model model){
        List<PostResponseDto> posts = postService.findAllPosts();
        if(posts != null){
            model.addAttribute("posts",posts);
        }
        return "index";
    }
}
