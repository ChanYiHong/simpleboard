package HCY.simpleboard.service;

import HCY.simpleboard.domain.Post;
import HCY.simpleboard.dto.post.PostSaveRequestDto;
import HCY.simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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
        return postRepository.save(requestDto.toEntity());
    }

}
