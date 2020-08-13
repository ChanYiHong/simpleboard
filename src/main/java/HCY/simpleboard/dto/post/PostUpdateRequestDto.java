package HCY.simpleboard.dto.post;

import HCY.simpleboard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Post를 Update하기 위해 필요한 정보를 담은 Dto **/
@Getter @Setter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

}
