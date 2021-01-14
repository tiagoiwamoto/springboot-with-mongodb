package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.repository;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:44
*/

import br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
