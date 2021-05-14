package br.com.tiagoiwamoto.api.controller;

import br.com.tiagoiwamoto.api.business.object.BlogPostBO;
import br.com.tiagoiwamoto.api.entity.BlogComment;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/05/2021 | 10:20
 */

@RunWith(SpringRunner.class)
public class BlogPostControllerTest {

    @InjectMocks
    private BlogPostController blogPostController;
    @Mock
    private BlogPostBO blogPostBO;

    @Test
    public void getPosts() {
        Mockito.when(this.blogPostBO.performRecoverPosts()).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogPostController.getPosts().getBody());
    }

    @Test
    public void createPost() {
        Mockito.when(this.blogPostBO.performSavePost(Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogPostController.createPost(new BlogPost()).getBody());
    }

    @Test
    public void likePost() {
        Mockito.when(this.blogPostBO.performLikePost(Mockito.any(), Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogPostController.likePost("", "").getBody());
    }

    @Test
    public void commentPost() {
        Mockito.when(this.blogPostBO.performCommentPost(Mockito.any(), Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogPostController.commentPost("", new BlogComment()).getBody());
    }
}
