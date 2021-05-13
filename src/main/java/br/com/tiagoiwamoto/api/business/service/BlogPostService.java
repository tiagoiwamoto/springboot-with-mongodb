package br.com.tiagoiwamoto.api.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 17:25
 */

import br.com.tiagoiwamoto.api.entity.BlogCategory;
import br.com.tiagoiwamoto.api.entity.BlogComment;
import br.com.tiagoiwamoto.api.entity.BlogPost;
import br.com.tiagoiwamoto.api.exception.CategoryRecoverException;
import br.com.tiagoiwamoto.api.exception.PostCreationException;
import br.com.tiagoiwamoto.api.exception.PostLikeException;
import br.com.tiagoiwamoto.api.exception.PostRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogCategoryRepository;
import br.com.tiagoiwamoto.api.repository.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogCategoryRepository blogCategoryRepository;

    public BlogPostService(BlogPostRepository blogPostRepository,
                           BlogCategoryRepository blogCategoryRepository) {
        this.blogPostRepository = blogPostRepository;
        this.blogCategoryRepository = blogCategoryRepository;
    }

    public BlogPost savePost(BlogPost post){
        try{
            Optional<BlogCategory> optionalBlogCategory = this.blogCategoryRepository.findById(post.getCategoryId());
            if(optionalBlogCategory.isPresent()){
                BlogPost blogPost = this.blogPostRepository.save(post);
                BlogCategory blogCategory = optionalBlogCategory.get();
                if(blogCategory.getPosts() == null){
                    blogCategory.setPosts(new ArrayList<>());
                }
                this.blogCategoryRepository.save(blogCategory);
                return blogPost;
            }else{
                throw new CategoryRecoverException();
            }
        }catch (Exception e){
            throw new PostCreationException();
        }
    }

    public List<BlogPost> recoverLastesPosts(){
        try{
            PageRequest pageRequest = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<BlogPost> pagePosts = this.blogPostRepository.findAll(pageRequest);
            return pagePosts.getContent();
        }catch (Exception e){
            throw new PostRecoverException();
        }
    }

    public List<BlogPost> recoverPostsByCategory(String category){
        try{
            return this.blogPostRepository.findAllByCategoryId(category);
        }catch (Exception e){
            throw new PostRecoverException();
        }
    }

    public BlogPost likePost(String postId, String userId){
        try{
            Optional<BlogPost> optionalBlogPost = this.blogPostRepository.findById(postId);
            if(optionalBlogPost.isPresent()){
                BlogPost blogPost = optionalBlogPost.get();
                if(blogPost.getLikes() == null){
                    blogPost.setLikes(new ArrayList<>());
                }

                /*
                    Busca se o usuário já deu like neste post
                    e só vai adicionar se o usuário não existir na lista.
                    desta forma um usuário não pode dar mais de um like.
                 */
                String userExists = blogPost.getLikes().stream().filter((String user) -> user.equals(userId)).findFirst().orElse(null);
                if(userExists == null){
                    blogPost.getLikes().add(userId);
                    this.blogPostRepository.save(blogPost);
                    return blogPost;
                }else{
                    throw new PostLikeException();
                }
            }else {
                throw new PostRecoverException();
            }
        }catch (Exception e){
            throw new PostRecoverException();
        }
    }

    public BlogPost commentPost(String postId, BlogComment comment){
        try{
            Optional<BlogPost> optionalBlogPost = this.blogPostRepository.findById(postId);
            if(optionalBlogPost.isPresent()){
                BlogPost blogPost = optionalBlogPost.get();
                if(blogPost.getComments() == null){
                    blogPost.setComments(new ArrayList<>());
                }
                blogPost.getComments().add(comment);
                this.blogPostRepository.save(blogPost);
                return blogPost;
            }else {
                throw new PostRecoverException();
            }
        }catch (Exception e){
            throw new PostRecoverException();
        }
    }
}
