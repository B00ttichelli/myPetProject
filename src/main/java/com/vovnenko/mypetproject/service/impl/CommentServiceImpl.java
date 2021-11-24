package com.vovnenko.mypetproject.service.impl;

import com.vovnenko.mypetproject.dto.CommentDto;
import com.vovnenko.mypetproject.exceptions.CustomException;
import com.vovnenko.mypetproject.mapper.CommentMapper;
import com.vovnenko.mypetproject.model.Comment;
import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.model.User;
import com.vovnenko.mypetproject.repository.CommentRepository;
import com.vovnenko.mypetproject.repository.PostRepository;
import com.vovnenko.mypetproject.security.repository.UserRepository;
import com.vovnenko.mypetproject.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentDto> findAllByPostIdPageable(Long id, Pageable pageable) {

        return commentRepository.findAllByPost_PostId(id,pageable).getContent()
                .stream().map(commentMapper::commentToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto create(CommentDto commentDto, String name) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow();
        User user = userRepository.findByUsername(name).orElseThrow();
        comment.setPost(post);
        comment.setUser(user);

        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }
}
