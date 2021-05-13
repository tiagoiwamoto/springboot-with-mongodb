package br.com.tiagoiwamoto.api.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 16:45
 */

import br.com.tiagoiwamoto.api.entity.BlogCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogCategoryRepository extends MongoRepository<BlogCategory, String> {
}
