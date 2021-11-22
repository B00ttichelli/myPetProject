package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.CommentDto;
import com.vovnenko.mypetproject.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/create")
    ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
                                          Authentication authentication) {

        CommentDto response = commentService.create(commentDto, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    ResponseEntity<List<CommentDto>> getCommentsByPostId(@RequestParam Long id,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "50") int size ){



        Pageable pageable = PageRequest.of(page, size);
        List<CommentDto> response  = commentService.findAllByPostIdPageable(id,pageable);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
