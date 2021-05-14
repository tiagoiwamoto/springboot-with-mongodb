package br.com.tiagoiwamoto.api.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 11:42
 */

import br.com.tiagoiwamoto.api.business.service.BlogUserService;
import br.com.tiagoiwamoto.api.entity.BlogUser;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogUserBO {

    private final BlogUserService blogUserService;

    public BlogUserBO(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    public ApiDTO<BlogUser> performLogin(String email, String password){
        BlogUser user = this.blogUserService.login(email, password);
        user.setPassword("******");
        return new ApiDTO<>(
                "0",
                "Usuário recuperado com sucesso",
                user
        );
    }

    public ApiDTO<BlogUser> performSave(BlogUser user){
        user.setCreatedAt(LocalDateTime.now());
        BlogUser userCreated = this.blogUserService.saveUser(user);
        userCreated.setPassword("******");
        return new ApiDTO<>(
                "0",
                "Usuário recuperado com sucesso",
                userCreated
        );
    }

}
