package br.com.tiagoiwamoto.api.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 11:37
 */

import br.com.tiagoiwamoto.api.entity.BlogUser;
import br.com.tiagoiwamoto.api.exception.UserCreationException;
import br.com.tiagoiwamoto.api.exception.UserRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogUserRepository;
import br.com.tiagoiwamoto.api.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BlogUserService {

    private final BlogUserRepository blogUserRepository;

    public BlogUserService(BlogUserRepository blogUserRepository) {
        this.blogUserRepository = blogUserRepository;
    }

    public BlogUser saveUser(BlogUser blogUser){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            return this.blogUserRepository.save(blogUser);
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new UserCreationException();
        }
    }

    public BlogUser login(String username, String password){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            Optional<BlogUser> optionalUser = this.blogUserRepository.findByEmailAndPassword(username, password);
            if(optionalUser.isPresent()){
                log.info(Constants.FINISHING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
                return optionalUser.get();
            }else{
                log.info("user not found on database");
                throw new UserRecoverException();
            }
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new UserRecoverException();
        }
    }
}
