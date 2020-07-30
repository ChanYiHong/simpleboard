package HCY.simpleboard.dto.post;

import HCY.simpleboard.domain.Post;
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

    @Builder
    public PostSaveRequestDto(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
