package br.com.tiagoiwamoto.api.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 17:22
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class BlogPost {

    @Id
    private String id;
    private String categoryId;
    private String title;
    private String post;
    private LocalDateTime createdAt;
    private List<String> likes;
    private List<BlogComment> comments;

}
