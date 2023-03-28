package com.doc.doctor.controller;

import com.doc.doctor.models.Comment;
import com.doc.doctor.models.Post;
import com.doc.doctor.service.CommentService;
import com.doc.doctor.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("home/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.finAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PostMapping("/addcomment")
    public Comment createCommentForPost(@PathVariable Long postId, @RequestBody Comment comment) {
        Optional<Post> optionalPost = postService.findPostById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            comment.setPost(post);
            return commentService.addComment(comment);
        } else {
            throw new RuntimeException("Post not found");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id")  Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
