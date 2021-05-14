package br.com.tiagoiwamoto.api.model.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/05/2021 | 10:31
 */

public class DtoTest {

    @Test
    public void testApiDto(){
        ApiDTO<String> apiDTO = new ApiDTO<>();
        apiDTO = new ApiDTO<>("", "", "");
        Assert.assertNotNull(apiDTO.getCode());
        Assert.assertNotNull(apiDTO.getMessage());
        Assert.assertNotNull(apiDTO.getDetails());
        Assert.assertNotNull(apiDTO.getProtocol());
        Assert.assertNotNull(apiDTO.getTimestamp());
        Assert.assertNotNull(apiDTO.toString());
    }

    @Test
    public void testBlogUserDto(){
        BlogUserDTO blogUserDTO = new BlogUserDTO();
        blogUserDTO.setId("123");
        blogUserDTO.setEmail("email@email.com");
        Assert.assertNotNull(blogUserDTO.getId());
        Assert.assertNotNull(blogUserDTO.getEmail());
        Assert.assertNotNull(blogUserDTO.toString());
    }

}
