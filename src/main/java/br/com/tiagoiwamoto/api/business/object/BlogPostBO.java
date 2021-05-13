package br.com.tiagoiwamoto.api.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 08:27
 */

import br.com.tiagoiwamoto.api.business.service.BlogPostService;
import br.com.tiagoiwamoto.api.entity.BlogComment;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BlogPostBO {

    private final BlogPostService blogPostService;

    public BlogPostBO(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    public ApiDTO<List<BlogPost>> performRecoverPosts(){
        return new ApiDTO<>("0",
                "",
                this.blogPostService.recoverLastesPosts());
    }

    public ApiDTO<BlogPost> performSavePost(BlogPost post){
        post.setCreatedAt(LocalDateTime.now());
        return new ApiDTO<>("0",
                "",
                this.blogPostService.savePost(post));
    }

    public ApiDTO<BlogPost> performLikePost(String post, String user){
        return new ApiDTO<>("0",
                "",
                this.blogPostService.likePost(post, user));
    }

    public ApiDTO<BlogPost> performCommentPost(String post, BlogComment comment){
        return new ApiDTO<>("0",
                "",
                this.blogPostService.commentPost(post, comment));
    }

}
