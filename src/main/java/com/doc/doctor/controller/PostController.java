package com.doc.doctor.controller;

import com.doc.doctor.models.Post;
import com.doc.doctor.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home/post")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.finAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        Post newPost = postService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post post){
        Post updatePost = postService.updatePost(post);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id")  Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
