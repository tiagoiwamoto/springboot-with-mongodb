package br.com.tiagoiwamoto.api.controller;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 08:28
 */

import br.com.tiagoiwamoto.api.business.object.BlogPostBO;
import br.com.tiagoiwamoto.api.entity.BlogComment;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api/posts")
public class BlogPostController {

    private final BlogPostBO blogPostBO;

    public BlogPostController(BlogPostBO blogPostBO) {
        this.blogPostBO = blogPostBO;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<List<BlogPost>>> getPosts(){
        return ResponseEntity.ok(this.blogPostBO.performRecoverPosts());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogPost>> createPost(@RequestBody BlogPost post){
        return ResponseEntity.ok(this.blogPostBO.performSavePost(post));
    }

    @PostMapping(path = "/like")
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogPost>> likePost(@RequestHeader(name = "x-post-id") String postid,
                                                     @RequestHeader(name = "x-user-id") String userid){
        return ResponseEntity.ok(this.blogPostBO.performLikePost(postid, userid));
    }

    @PostMapping(path = "/comment")
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogPost>> commentPost(@RequestHeader(name = "x-post-id") String postid,
                                                        @RequestBody BlogComment comment){
        return ResponseEntity.ok(this.blogPostBO.performCommentPost(postid, comment));
    }

}
