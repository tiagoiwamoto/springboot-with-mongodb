package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.controller;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:54
*/

import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.business.object.UserBO;
import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.dto.ApiDTO;
import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UserAPI {

    private final UserBO userBO;

    public UserAPI(UserBO userBO) {
        this.userBO = userBO;
    }

    @PostMapping
    public ApiDTO<User> create(@RequestBody User user){
        return this.userBO.saveUser(user);
    }

    @GetMapping
    public ApiDTO<List<User>> index(){
        return this.userBO.allUsers();
    }
}
