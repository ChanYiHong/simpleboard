package HCY.simpleboard.service;

import HCY.simpleboard.domain.Post;
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
    public Long savePost(PostSaveRequestDto requestDto){
        Post post = requestDto.toEntity();
        postRepository.save(post);
        System.out.println(post.getId());
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

    @Transactional
    public Long updatePost(PostUpdateRequestDto updateRequestDto, Long id){
        Post post = postRepository.findById(id);
        post.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return id;
    }

    public PostResponseDto findPostByTitle(String title){
        List<Post> posts = postRepository.findByTitle(title);
        Post post = posts.get(0);
        PostResponseDto dto = PostResponseDto.builder().post(post).build();
        return dto;
    }
}
