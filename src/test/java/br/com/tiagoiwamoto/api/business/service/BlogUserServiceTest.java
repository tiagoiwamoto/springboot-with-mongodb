package br.com.tiagoiwamoto.api.business.service;

import br.com.tiagoiwamoto.api.entity.BlogUser;
import br.com.tiagoiwamoto.api.exception.UserCreationException;
import br.com.tiagoiwamoto.api.exception.UserRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/05/2021 | 10:03
 */

@RunWith(SpringRunner.class)
public class BlogUserServiceTest {

    @InjectMocks
    private BlogUserService blogUserService;
    @Mock
    private BlogUserRepository blogUserRepository;

    @Test
    public void saveUser() {
        Mockito.when(this.blogUserRepository.save(Mockito.any())).thenReturn(new BlogUser());
        Assert.assertNotNull(this.blogUserService.saveUser(new BlogUser()));
    }

    @Test(expected = UserCreationException.class)
    public void saveUserEx() {
        Mockito.when(this.blogUserRepository.save(Mockito.any())).thenThrow(NullPointerException.class);
        this.blogUserService.saveUser(new BlogUser());
    }

    @Test
    public void login() {
        Mockito.when(this.blogUserRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(new BlogUser()));
        Assert.assertNotNull(this.blogUserService.login("", ""));
    }

    @Test(expected = UserRecoverException.class)
    public void loginNotFound() {
        Mockito.when(this.blogUserRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
        this.blogUserService.login("", "");
    }

    @Test(expected = UserRecoverException.class)
    public void loginEx() {
        Mockito.when(this.blogUserRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(NullPointerException.class);
        this.blogUserService.login("", "");
    }
}
