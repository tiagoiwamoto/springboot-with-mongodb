package br.com.tiagoiwamoto.api.controller;

import br.com.tiagoiwamoto.api.business.object.BlogCategoryBO;
import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.exception.CategoryDeleteException;
import br.com.tiagoiwamoto.api.exception.CategoryUpdateException;
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
 * 14/05/2021 | 10:11
 */

@RunWith(SpringRunner.class)
public class BlogCategoryControllerTest {

    @InjectMocks
    private BlogCategoryController blogCategoryController;
    @Mock
    private BlogCategoryBO blogCategoryBO;

    @Test
    public void getCategories() {
        Mockito.when(this.blogCategoryBO.performRecoverCategories()).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogCategoryController.getCategories().getBody());
    }

    @Test
    public void createCategory() {
        Mockito.when(this.blogCategoryBO.performSaveCategory(Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogCategoryController.createCategory(new BlogCategory()).getBody());
    }

    @Test
    public void updateCategory() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId("123");
        Mockito.when(this.blogCategoryBO.performSaveCategory(Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogCategoryController.updateCategory(blogCategory).getBody());

    }

    @Test(expected = CategoryUpdateException.class)
    public void updateCategoryEx() {
        Mockito.when(this.blogCategoryBO.performSaveCategory(Mockito.any())).thenReturn(new ApiDTO<>());
        this.blogCategoryController.updateCategory(new BlogCategory());
    }

    @Test
    public void deleteCategory() {
        Mockito.when(this.blogCategoryBO.performRemoveCategory(Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogCategoryController.deleteCategory("123").getBody());
    }

    @Test(expected = CategoryDeleteException.class)
    public void deleteCategoryEx() {
        Mockito.when(this.blogCategoryBO.performRemoveCategory(Mockito.any())).thenReturn(new ApiDTO<>());
        this.blogCategoryController.deleteCategory("");
    }
}
