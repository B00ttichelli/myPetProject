package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.PostDto;
import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/get")
    ResponseEntity<List<PostDto>> getPostsByForumId(@RequestParam Long id,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<PostDto> response = postService.findAllByForumIdPageable(id, pageable);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
