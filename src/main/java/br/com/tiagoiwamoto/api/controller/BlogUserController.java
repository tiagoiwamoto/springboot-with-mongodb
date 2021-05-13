package br.com.tiagoiwamoto.api.controller;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 11:46
 */

import br.com.tiagoiwamoto.api.business.object.BlogUserBO;
import br.com.tiagoiwamoto.api.entity.BlogUser;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/users")
public class BlogUserController {

    private final BlogUserBO blogUserBO;

    public BlogUserController(BlogUserBO blogUserBO) {
        this.blogUserBO = blogUserBO;
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogUser>> login(@RequestHeader(name = "x-blog-username") String email,
                                                  @RequestHeader(name = "x-blog-password") String password){
        return ResponseEntity.ok(this.blogUserBO.performLogin(email, password));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogUser>> create(@RequestBody BlogUser user){
        return ResponseEntity.ok(this.blogUserBO.performSave(user));
    }



}
