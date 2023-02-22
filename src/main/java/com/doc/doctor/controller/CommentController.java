package com.doc.doctor.controller;

import com.doc.doctor.models.Comment;
import com.doc.doctor.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home/comment")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllPosts(){
        List<Comment> comments = commentService.finAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Comment> addPost(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Comment> updatePost(@RequestBody Comment comment){
        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deletePost(@PathVariable("id")  Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
