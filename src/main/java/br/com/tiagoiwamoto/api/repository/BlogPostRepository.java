package br.com.tiagoiwamoto.api.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 17:24
 */

import br.com.tiagoiwamoto.api.entity.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

    List<BlogPost> findAllByCategoryId(String categoryId);

}
