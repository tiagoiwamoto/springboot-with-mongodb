package br.com.tiagoiwamoto.api.business.object;

import br.com.tiagoiwamoto.api.business.service.BlogCategoryService;
import br.com.tiagoiwamoto.api.entity.BlogCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 12:07
 */

@RunWith(SpringRunner.class)
public class BlogCategoryBOTest {

    @InjectMocks
    private BlogCategoryBO blogCategoryBO;
    @Mock
    private BlogCategoryService blogCategoryService;

    @Test
    public void performSaveCategory() {
        Mockito.when(this.blogCategoryService.saveCategory(Mockito.any())).thenReturn(new BlogCategory());
        Assert.assertNotNull(this.blogCategoryBO.performSaveCategory(new BlogCategory()));
    }

    @Test
    public void performRecoverCategories() {
        Mockito.when(this.blogCategoryService.recoverCategories()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(this.blogCategoryBO.performRecoverCategories());
    }

    @Test
    public void performRemoveCategory() {
        Mockito.doNothing().when(this.blogCategoryService).deleteCategory(Mockito.anyString());
        Assert.assertNotNull(this.blogCategoryBO.performRemoveCategory(""));
    }
}
