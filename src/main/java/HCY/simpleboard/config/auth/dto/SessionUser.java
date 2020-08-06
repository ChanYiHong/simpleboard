package HCY.simpleboard.config.auth.dto;

import HCY.simpleboard.domain.user.User;
import lombok.Getter;

/** Session에는 인증된 사용자 정보만 필요함. 따라서 필요없는 정보는 제외됨 **/
/** 세션에 사용자 정보를 저장하기 위한 Dto 클래스 .**/

/** User 클래스는 직렬화를 구현하지 않음. (데이터를 주고 받기 위한 포멧을 맞추는 일)
 * 그래서 직렬화 기능을 가지는 세션 Dto를 하나 추가로 만든 것. **/
@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
