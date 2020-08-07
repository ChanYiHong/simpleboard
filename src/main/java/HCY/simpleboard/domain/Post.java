package HCY.simpleboard.domain;

import HCY.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"title"})})
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    // Post 와 User 가 다 대 1 관계 이므로 연관관계의 주인은 Post가 됨. (DB에서도 FK가 Post에 있음)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Lob
    private String content;

    @Builder
    public Post(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    // == 연관 관계 메서드 정의 == //
    // == 사용자가 게시글을 생성하면 Post와 User 양쪽 모두에 값을 저장해주어야 함 == //

    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }
}
