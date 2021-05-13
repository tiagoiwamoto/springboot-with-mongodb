package br.com.tiagoiwamoto.api.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 16:58
 */

import br.com.tiagoiwamoto.api.business.service.BlogCategoryService;
import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.model.dto.ApiDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BlogCategoryBO {

    private BlogCategoryService blogCategoryService;

    public BlogCategoryBO(BlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    public ApiDTO<BlogCategory> performSaveCategory(BlogCategory category){
        category.setCreatedAt(LocalDateTime.now());
        return new ApiDTO<>(
                "0",
                "Executado com sucesso",
                this.blogCategoryService.saveCategory(category));
    }

    public ApiDTO<List<BlogCategory>> performrecoverCategories(){
        return new ApiDTO<>(
                "0",
                "Categorias recuperadas com sucesso",
                this.blogCategoryService.recoverCategories());
    }

    public ApiDTO<String> performRemoveCategory(String id){
        this.blogCategoryService.deleteCategory(id);
        return new ApiDTO<>(
                "0",
                "Categoria removida com sucesso",
                "");
    }
}
