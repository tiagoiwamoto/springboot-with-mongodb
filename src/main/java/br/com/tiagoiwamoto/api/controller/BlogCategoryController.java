package br.com.tiagoiwamoto.api.controller;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 17:02
 */

import br.com.tiagoiwamoto.api.business.object.BlogCategoryBO;
import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.exception.CategoryDeleteException;
import br.com.tiagoiwamoto.api.exception.CategoryUpdateException;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api/categories")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryBO blogCategoryBO;

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<List<BlogCategory>>> getCategories(){
        return ResponseEntity.ok(this.blogCategoryBO.performrecoverCategories());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogCategory>> createCategory(@RequestBody BlogCategory category){
        return ResponseEntity.ok(this.blogCategoryBO.performSaveCategory(category));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<BlogCategory>> updateCategory(@RequestBody BlogCategory category){
        if(category.getId() == null || category.getId().isEmpty()){
            throw new CategoryUpdateException();
        }
        return ResponseEntity.ok(this.blogCategoryBO.performSaveCategory(category));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ApiDTO<String>> deleteCategory(@RequestHeader(name = "x-category-id") String id){
        if(id == null ||id.isEmpty()){
            throw new CategoryDeleteException();
        }
        return ResponseEntity.ok(this.blogCategoryBO.performRemoveCategory(id));
    }

}
