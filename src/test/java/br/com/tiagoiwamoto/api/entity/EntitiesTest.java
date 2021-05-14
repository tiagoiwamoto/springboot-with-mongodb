package br.com.tiagoiwamoto.api.entity;

import br.com.tiagoiwamoto.api.model.dto.BlogUserDTO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/05/2021 | 10:26
 */

public class EntitiesTest {

    @Test
    public void testBlogCategory(){
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId("123");
        blogCategory.setName("category");
        blogCategory.setDescription("description");
        blogCategory.setCreatedAt(LocalDateTime.now());
        Assert.assertNotNull(blogCategory.getId());
        Assert.assertNotNull(blogCategory.getName());
        Assert.assertNotNull(blogCategory.getDescription());
        Assert.assertNotNull(blogCategory.getCreatedAt());
        Assert.assertNotNull(blogCategory.toString());
    }

    @Test
    public void testBlogPost(){
        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        blogPost.setLikes(new ArrayList<>());
        blogPost.setComments(new ArrayList<>());
        Assert.assertNotNull(blogPost.getId());
        Assert.assertNotNull(blogPost.getTitle());
        Assert.assertNotNull(blogPost.getPost());
        Assert.assertNotNull(blogPost.getCreatedAt());
        Assert.assertNotNull(blogPost.getCategoryId());
        Assert.assertNotNull(blogPost.getLikes());
        Assert.assertNotNull(blogPost.getComments());
        Assert.assertNotNull(blogPost.toString());
    }

    @Test
    public void testBlogUser(){
        BlogUser blogUser = new BlogUser();
        blogUser.setId("123");
        blogUser.setEmail("email@email.com");
        blogUser.setPassword("******");
        blogUser.setCreatedAt(LocalDateTime.now());
        Assert.assertNotNull(blogUser.getId());
        Assert.assertNotNull(blogUser.getEmail());
        Assert.assertNotNull(blogUser.getPassword());
        Assert.assertNotNull(blogUser.getCreatedAt());
        Assert.assertNotNull(blogUser.toString());
    }

    @Test
    public void testBlogComment(){
        BlogComment blogComment = new BlogComment();
        blogComment.setComment("");
        blogComment.setBlogUser(new BlogUserDTO());
        Assert.assertNotNull(blogComment.getComment());
        Assert.assertNotNull(blogComment.getBlogUser());
        Assert.assertNotNull(blogComment.toString());
    }

}
