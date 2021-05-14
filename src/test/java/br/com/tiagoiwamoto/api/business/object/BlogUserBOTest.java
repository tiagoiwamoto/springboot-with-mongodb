package br.com.tiagoiwamoto.api.business.object;

import br.com.tiagoiwamoto.api.business.service.BlogUserService;
import br.com.tiagoiwamoto.api.entity.BlogUser;
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
 * 14/05/2021 | 07:44
 */

@RunWith(SpringRunner.class)
public class BlogUserBOTest {

    @InjectMocks
    private BlogUserBO blogUserBO;
    @Mock
    private BlogUserService blogUserService;

    @Test
    public void performLogin() {
        Mockito.when(this.blogUserService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(new BlogUser());
        Assert.assertNotNull(this.blogUserBO.performLogin("", ""));
    }

    @Test
    public void performSave() {
        Mockito.when(this.blogUserService.saveUser(Mockito.any())).thenReturn(new BlogUser());
        Assert.assertNotNull(this.blogUserBO.performSave(new BlogUser()));
    }
}
