package br.com.tiagoiwamoto.api.business.service;

import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.entity.BlogComment;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.exception.PostCreationException;
import br.com.tiagoiwamoto.api.exception.PostLikeException;
import br.com.tiagoiwamoto.api.exception.PostRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogPostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/05/2021 | 08:15
 */

@RunWith(SpringRunner.class)
public class BlogPostServiceTest {

    @InjectMocks
    private BlogPostService blogPostService;
    @Mock
    private BlogPostRepository blogPostRepository;
    @Mock
    private BlogCategoryService blogCategoryService;

    @Test
    public void savePost() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId("123");
        blogCategory.setName("category");
        blogCategory.setDescription("description");
        blogCategory.setCreatedAt(LocalDateTime.now());

        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        Mockito.when(this.blogCategoryService.recoverCategory(Mockito.anyString())).thenReturn(blogCategory);
        Mockito.when(this.blogPostRepository.save(Mockito.any())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostService.savePost(blogPost));
    }

    @Test(expected = PostCreationException.class)
    public void savePostEx() {
        Mockito.when(this.blogCategoryService.recoverCategory(Mockito.anyString())).thenReturn(new BlogCategory());
        Mockito.when(this.blogPostRepository.save(Mockito.any())).thenThrow(NullPointerException.class);
        this.blogPostService.savePost(new BlogPost());
    }

    @Test
    public void recoverLastesPosts() {
        Page<BlogPost> page = new PageImpl<>(new ArrayList<>());
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "createdAt"));
        Mockito.when(this.blogPostRepository.findAll(pageRequest)).thenReturn(page);
        Assert.assertNotNull(this.blogPostService.recoverLastesPosts());
    }

    @Test(expected = PostRecoverException.class)
    public void recoverLastesPostsEx() {
        Mockito.when(this.blogPostRepository.findAll()).thenThrow(NullPointerException.class);
        this.blogPostService.recoverLastesPosts();
    }

    @Test
    public void recoverPostsByCategory() {
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new BlogPost()));
        Assert.assertNotNull(this.blogPostService.recoverPostsByCategory(""));
    }

    @Test(expected = PostRecoverException.class)
    public void recoverPostsByCategoryEx() {
        Mockito.when(this.blogPostRepository.findAllByCategoryId(Mockito.anyString())).thenThrow(NullPointerException.class);
        this.blogPostService.recoverPostsByCategory("");
    }

    @Test
    public void likePost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.of(blogPost));
        Assert.assertNotNull(this.blogPostService.likePost("", ""));
    }

    @Test(expected = PostLikeException.class)
    public void likePostUserAlreadyLikePost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        List<String> likes = new ArrayList<>();
        likes.add("123");
        blogPost.setLikes(likes);
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.of(blogPost));
        Mockito.when(this.blogPostRepository.save(Mockito.any())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostService.likePost("", "123"));
    }

    @Test(expected = PostRecoverException.class)
    public void likePostNotFound() {
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        this.blogPostService.likePost("", "");
    }

    @Test(expected = PostLikeException.class)
    public void likePostEx() {
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenThrow(NullPointerException.class);
        this.blogPostService.likePost("", "");
    }

    @Test
    public void commentPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.of(blogPost));
        Mockito.when(this.blogPostRepository.save(Mockito.any())).thenReturn(new BlogPost());
        Assert.assertNotNull(this.blogPostService.commentPost("", new BlogComment()));
    }

    @Test(expected = PostRecoverException.class)
    public void commentPostNotFound() {
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        this.blogPostService.commentPost("", new BlogComment());
    }

    @Test(expected = PostRecoverException.class)
    public void commentPostEx() {
        Mockito.when(this.blogPostRepository.findById(Mockito.anyString())).thenThrow(NullPointerException.class);
        this.blogPostService.commentPost("", new BlogComment());
    }
}
