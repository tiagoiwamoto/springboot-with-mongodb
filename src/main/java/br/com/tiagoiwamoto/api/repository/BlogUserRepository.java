package br.com.tiagoiwamoto.api.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 11:37
 */

import br.com.tiagoiwamoto.api.entity.BlogUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlogUserRepository extends MongoRepository<BlogUser, String> {

    Optional<BlogUser> findByEmailAndPassword(String email, String password);

}
