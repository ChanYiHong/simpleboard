package HCY.simpleboard.dto.post;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String author;
    private String content;
    private User user;

    @Builder
    public PostSaveRequestDto(String title, String author, String content, User user){
        this.title = title;
        this.author = author;
        this.content = content;
        this.user = user;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
