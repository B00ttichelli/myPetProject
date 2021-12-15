package com.vovnenko.mypetproject.service.impl;

import com.vovnenko.mypetproject.dto.PostDto;
import com.vovnenko.mypetproject.mapper.PostMapper;
import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.model.SubForum;
import com.vovnenko.mypetproject.model.User;
import com.vovnenko.mypetproject.repository.PostRepository;
import com.vovnenko.mypetproject.repository.SubForumRepository;
import com.vovnenko.mypetproject.security.repository.UserRepository;
import com.vovnenko.mypetproject.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final SubForumRepository subForumRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public PostDto create(PostDto postDto, String name) {
        Post post = postMapper.postDtoToPost(postDto);
        User user = userRepository.findByUsername(name).orElseThrow();
        post.setUser(user);
        SubForum subForum = subForumRepository.findById(postDto.getSubForumId()).orElseThrow();
        post.setSubForum(subForum);
        postRepository.save(post);
        Post posted = postRepository.getByPostName(postDto.getPostName());

        return postMapper.postToPostDto(posted);
    }

    @Transactional
    @Override
    public List<PostDto> findAllByForumIdPageable(Long id, Pageable pageable) {
        return postRepository.findAllBySubForum_Id(id, pageable).getContent().stream().map(postMapper::postToPostDto).collect(Collectors.toList());

    }

    @Override
    public PostDto getById(Long id) {
        return postMapper.postToPostDto(postRepository.getById(id));
    }
}
