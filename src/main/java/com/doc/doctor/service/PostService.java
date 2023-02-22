package com.doc.doctor.service;

import com.doc.doctor.models.Post;
import com.doc.doctor.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post){

        return postRepository.save(post);
    }
    public List<Post> finAllPost(){

        return postRepository.findAll();
    }
    public Post updatePost(Post post){

        return postRepository.save(post);
    }
    public void deletePost(Long id){

        postRepository.deleteById(id);
    }
//    public Post findPostById(Long id){
//        return postRepository.findPostById(id).orElseThrow(() -> new PostNotFoundException("Post by id "+ id + " was not flound"));
//    }

}
