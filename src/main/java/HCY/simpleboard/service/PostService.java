package HCY.simpleboard.service;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.domain.user.User;
import HCY.simpleboard.dto.post.PostResponseDto;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.dto.post.PostUpdateRequestDto;
import HCY.simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(PostSaveRequestDto requestDto, User user){
        // PostSaveRequestDto 를 Post entity로 변환! db에 저장하기 위함.
        Post post = requestDto.toEntity();
        // 연관관계 메서드를 통해 양쪽 table 모두 저장. (User, Post)
        post.setUser(user);
        postRepository.save(post);
        return post.getId();
    }

    public List<PostResponseDto> findAllPosts(){
        List<Post> posts = postRepository.findAll();
        return new PostResponseDto().toEntities(posts);
    }

    public PostResponseDto findPostById(Long id){
        Post post = postRepository.findById(id);
        return PostResponseDto.builder().post(post).build();
    }

    public PostResponseDto findPostByTitle(String title){
        List<Post> posts = postRepository.findByTitle(title);
        // 어짜피 하나만 있으니.. collection의 맨 앞 값 (0) 에 접근.
        Post post = posts.get(0);
        PostResponseDto dto = PostResponseDto.builder().post(post).build();
        return dto;
    }

    @Transactional
    public Long updatePost(PostUpdateRequestDto updateRequestDto, Long id){
        Post post = postRepository.findById(id);
        post.update(updateRequestDto.getTitle(), updateRequestDto.getContent());
        return id;
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id);
        postRepository.deletePost(post);
    }
}
