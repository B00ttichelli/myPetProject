package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.PostDto;
import com.vovnenko.mypetproject.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto){
        PostDto response  = postService.create(postDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
