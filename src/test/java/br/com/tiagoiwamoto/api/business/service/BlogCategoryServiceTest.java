package br.com.tiagoiwamoto.api.business.service;

import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.exception.CategoryCreationException;
import br.com.tiagoiwamoto.api.exception.CategoryDeleteException;
import br.com.tiagoiwamoto.api.exception.CategoryRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
 * 14/05/2021 | 08:04
 */

@RunWith(SpringRunner.class)
public class BlogCategoryServiceTest {

    @InjectMocks
    private BlogCategoryService blogCategoryService;
    @Mock
    private BlogCategoryRepository blogCategoryRepository;
    @Mock
    private BlogPostService blogPostService;

    @Test
    public void recoverCategories() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId("123");
        blogCategory.setName("category");
        blogCategory.setDescription("description");
        blogCategory.setCreatedAt(LocalDateTime.now());
        List<BlogCategory> categoryList = new ArrayList<>();
        categoryList.add(blogCategory);

        BlogPost blogPost = new BlogPost();
        blogPost.setId("123");
        blogPost.setTitle("Title");
        blogPost.setPost("post");
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setCategoryId("category");
        List<BlogPost> postList = new ArrayList<>();
        postList.add(blogPost);
        Mockito.when(this.blogCategoryRepository.findAll()).thenReturn(categoryList);
        Mockito.when(this.blogPostService.recoverPostsByCategory(Mockito.any())).thenReturn(postList);
        Assert.assertNotNull(this.blogCategoryService.recoverCategories());
    }

    @Test(expected = CategoryRecoverException.class)
    public void recoverCategoriesEx() {
        Mockito.when(this.blogCategoryRepository.findAll()).thenThrow(NullPointerException.class);
        this.blogCategoryService.recoverCategories();
    }

    @Test
    public void recoverCategory() {
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
        List<BlogPost> postList = new ArrayList<>();
        postList.add(blogPost);
        Mockito.when(this.blogCategoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(blogCategory));
        Mockito.when(this.blogPostService.recoverPostsByCategory(Mockito.any())).thenReturn(postList);
        Assert.assertNotNull(this.blogCategoryService.recoverCategories());
    }

    @Test(expected = CategoryRecoverException.class)
    public void recoverCategoryEx() {
        Mockito.when(this.blogCategoryRepository.findAll()).thenThrow(NullPointerException.class);
        this.blogCategoryService.recoverCategories();
    }

    @Test
    public void saveCategory() {
        Mockito.when(this.blogCategoryRepository.save(Mockito.any())).thenReturn(new BlogCategory());
        Assert.assertNotNull(this.blogCategoryService.saveCategory(new BlogCategory()));
    }

    @Test(expected = CategoryCreationException.class)
    public void saveCategoryEx() {
        Mockito.when(this.blogCategoryRepository.save(Mockito.any())).thenThrow(NullPointerException.class);
        this.blogCategoryService.saveCategory(new BlogCategory());
    }

    @Test
    public void deleteCategory() {
        Mockito.doNothing().when(this.blogCategoryRepository).deleteById(Mockito.anyString());
        this.blogCategoryService.deleteCategory("");
        Assert.assertNotNull(this.blogCategoryService);
    }

    @Test(expected = CategoryDeleteException.class)
    public void deleteCategoryEx() {
        Mockito.doThrow(NullPointerException.class).when(this.blogCategoryRepository).deleteById(Mockito.anyString());
        this.blogCategoryService.deleteCategory("");
    }
}
