package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.CommentDto;
import com.vovnenko.mypetproject.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/create")
    ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
                                          Authentication authentication){

        CommentDto response = commentService.create(commentDto,authentication.getName());
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }
}
