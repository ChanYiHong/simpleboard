package HCY.simpleboard.controller;

import HCY.simpleboard.config.auth.LoginUser;
import HCY.simpleboard.config.auth.dto.SessionUser;
import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    /** 어느 컨트롤러던 @LoginUser를 사용하면 세션 정보를 가져올 수 있음 **/
    @GetMapping(value = "/")
    public String index(Model model, @LoginUser SessionUser user){
        List<PostResponseDto> posts = postService.findAllPosts();

        model.addAttribute("posts",posts);

        if(user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }
}
