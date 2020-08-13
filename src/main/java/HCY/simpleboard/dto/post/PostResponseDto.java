package HCY.simpleboard.dto.post;

import HCY.simpleboard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** Post를 View에 띄우기 위한 Dto **/
@Getter @Setter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;

    @Builder
    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
    }

    public Post toEntity(){
        return Post.builder().title(this.title).author(this.author).content(this.content).build();
    }

    // == findAllPost에서 Post를 PostResponseDto로 만든다! == /
    public List<PostResponseDto> toEntities(List<Post> posts){
        List<PostResponseDto> responseDtos = new ArrayList<>();
        for(Post post : posts){
            responseDtos.add(new PostResponseDto(post));
        }
        return responseDtos;
    }
}
