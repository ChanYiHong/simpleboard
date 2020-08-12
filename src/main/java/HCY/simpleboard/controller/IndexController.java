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

        /** 모든 Post (게시물)를 메인 화면에 뿌리기 위해 서비스 계층을 통해 전부 다 찾아옴 **/
        List<PostResponseDto> posts = postService.findAllPosts();

        /** model에 posts 라는 이름의 attribute로 posts object를 넘긴다 **/
        model.addAttribute("posts",posts);

        /** 아직 로그인이 안된 경우를 체크 **/
        if(user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }
}
