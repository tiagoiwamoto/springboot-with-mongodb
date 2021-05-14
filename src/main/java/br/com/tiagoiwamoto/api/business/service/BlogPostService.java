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
import br.com.tiagoiwamoto.api.exception.PostCreationException;
import br.com.tiagoiwamoto.api.exception.PostLikeException;
import br.com.tiagoiwamoto.api.exception.PostRecoverException;
import br.com.tiagoiwamoto.api.repository.BlogPostRepository;
import br.com.tiagoiwamoto.api.util.Constants;
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
    private final BlogCategoryService blogCategoryService;

    public BlogPostService(BlogPostRepository blogPostRepository,
                           BlogCategoryService blogCategoryService) {
        this.blogPostRepository = blogPostRepository;
        this.blogCategoryService = blogCategoryService;
    }

    public BlogPost savePost(BlogPost post){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            BlogCategory blogCategory = this.blogCategoryService.recoverCategory(post.getCategoryId());
            BlogPost blogPost = this.blogPostRepository.save(post);
            this.blogCategoryService.saveCategory(blogCategory);
            return blogPost;
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new PostCreationException();
        }
    }

    public List<BlogPost> recoverLastesPosts(){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            PageRequest pageRequest = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<BlogPost> pagePosts = this.blogPostRepository.findAll(pageRequest);
            return pagePosts.getContent();
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new PostRecoverException();
        }
    }

    public List<BlogPost> recoverPostsByCategory(String category){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            return this.blogPostRepository.findAllByCategoryId(category);
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new PostRecoverException();
        }
    }

    public BlogPost likePost(String postId, String userId){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
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
                    log.info(Constants.FINISHING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
                    return blogPost;
                }else{
                    log.warn("User already liked this post");
                    throw new PostLikeException();
                }
            }else {
                log.error("This post dont exists on database -> ".concat(postId));
                throw new PostRecoverException();
            }
        }catch (PostRecoverException pe){
            throw new PostRecoverException();
        } catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new PostLikeException();
        }
    }

    public BlogPost commentPost(String postId, BlogComment comment){
        log.info(Constants.STARTING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
        try{
            Optional<BlogPost> optionalBlogPost = this.blogPostRepository.findById(postId);
            if(optionalBlogPost.isPresent()){
                BlogPost blogPost = optionalBlogPost.get();
                if(blogPost.getComments() == null){
                    blogPost.setComments(new ArrayList<>());
                }
                blogPost.getComments().add(comment);
                this.blogPostRepository.save(blogPost);
                log.info(Constants.FINISHING.concat(Thread.currentThread().getName().concat(Constants.METHOD)));
                return blogPost;
            }else {
                log.error("This post dont exists on database -> ".concat(postId));
                throw new PostRecoverException();
            }
        }catch (Exception e){
            log.error(Constants.FAILED.concat(e.toString()));
            throw new PostRecoverException();
        }
    }
}
