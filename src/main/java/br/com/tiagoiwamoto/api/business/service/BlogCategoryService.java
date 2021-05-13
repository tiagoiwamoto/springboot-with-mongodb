package br.com.tiagoiwamoto.api.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 16:47
 */

import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.exception.CategoryCreationException;
import br.com.tiagoiwamoto.api.exception.CategoryDeleteException;
import br.com.tiagoiwamoto.api.exception.CategoryRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;
    private final BlogPostService blogPostService;

    public BlogCategoryService(BlogCategoryRepository blogCategoryRepository,
                               BlogPostService blogPostService) {
        this.blogCategoryRepository = blogCategoryRepository;
        this.blogPostService = blogPostService;
    }

    public List<BlogCategory> recoverCategories(){
        try{
            List<BlogCategory> categories = this.blogCategoryRepository.findAll();
            categories.forEach((BlogCategory category) -> {
                category.setPosts(new ArrayList<>());
                category.getPosts().addAll(this.blogPostService.recoverPostsByCategory(category.getId()));
            });
            return categories;
        }catch (Exception e){
            throw new CategoryRecoverException();
        }
    }

    public BlogCategory saveCategory(BlogCategory category){
        try{
            return this.blogCategoryRepository.save(category);
        }catch (Exception e){
            throw new CategoryCreationException();
        }
    }

    public void deleteCategory(String id){
        try{
            this.blogCategoryRepository.deleteById(id);
        }catch (Exception e){
            throw new CategoryDeleteException();
        }
    }
}
