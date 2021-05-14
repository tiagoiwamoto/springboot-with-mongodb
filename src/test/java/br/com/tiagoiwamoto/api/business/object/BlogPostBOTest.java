package br.com.tiagoiwamoto.api.business.object;

import br.com.tiagoiwamoto.api.business.service.BlogPostService;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 22:09
 */

@RunWith(SpringRunner.class)
public class BlogPostBOTest {

    @InjectMocks
    private BlogPostBO blogPostBO;
    @Mock
    private BlogPostService blogPostService;

    @Test
    public void performRecoverPosts() {
        Mockito.when(this.blogPostService.recoverLastesPosts()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(this.blogPostBO.performRecoverPosts());
    }

    @Test
    public void performSavePost() {
        Mockito.when(this.blogPostService.savePost(Mockito.any())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostBO.performSavePost(new BlogPost()));
    }

    @Test
    public void performLikePost() {
        Mockito.when(this.blogPostService.likePost(Mockito.anyString(), Mockito.anyString())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostBO.performLikePost("", ""));
    }

    @Test
    public void performCommentPost() {
        Mockito.when(this.blogPostService.commentPost(Mockito.anyString(), Mockito.any())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostBO.performCommentPost(Mockito.anyString(), Mockito.any()));
    }
}
