package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.business.object;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:46
*/

import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.dto.ApiDTO;
import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity.User;
import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserBO {

    private final UserRepository userRepository;

    public UserBO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiDTO<User> saveUser(User user) {
        try{
            user.setCreatedAt(LocalDateTime.now());
            ApiDTO<User> apiDTO = new ApiDTO<>();
            apiDTO.setCode("0");
            apiDTO.setMessage("Success");
            apiDTO.setDetail(this.userRepository.save(user));
            apiDTO.setTimestamp(LocalDateTime.now());
            return apiDTO;
        }catch (Exception e){
            ApiDTO<User> apiDTO = new ApiDTO<>();
            apiDTO.setCode("1");
            apiDTO.setMessage(e.getMessage());
            apiDTO.setDetail(null);
            apiDTO.setTimestamp(LocalDateTime.now());
            return apiDTO;
        }
    }

    public ApiDTO<List<User>> allUsers(){
        try{
            ApiDTO<List<User>> apiDTO = new ApiDTO<>();
            apiDTO.setCode("0");
            apiDTO.setMessage("Success");
            apiDTO.setDetail(this.userRepository.findAll());
            apiDTO.setTimestamp(LocalDateTime.now());
            return apiDTO;
        }catch (Exception e){
            ApiDTO<List<User>> apiDTO = new ApiDTO<>();
            apiDTO.setCode("1");
            apiDTO.setMessage(e.getMessage());
            apiDTO.setDetail(null);
            apiDTO.setTimestamp(LocalDateTime.now());
            return apiDTO;
        }
    }
}
