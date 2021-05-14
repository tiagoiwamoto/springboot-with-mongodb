package br.com.tiagoiwamoto.api.controller;

import br.com.tiagoiwamoto.api.business.object.BlogUserBO;
import br.com.tiagoiwamoto.api.entity.BlogUser;
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
 * 14/05/2021 | 10:22
 */

@RunWith(SpringRunner.class)
public class BlogUserControllerTest {

    @InjectMocks
    private BlogUserController blogUserController;
    @Mock
    private BlogUserBO blogUserBO;

    @Test
    public void login() {
        Mockito.when(this.blogUserBO.performLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogUserController.login("", "").getBody());
    }

    @Test
    public void create() {
        Mockito.when(this.blogUserBO.performSave(Mockito.any())).thenReturn(new ApiDTO<>());
        Assert.assertNotNull(this.blogUserController.create(new BlogUser()).getBody());
    }
}
