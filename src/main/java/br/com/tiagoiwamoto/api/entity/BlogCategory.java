package br.com.tiagoiwamoto.api.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 16:23
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class BlogCategory {

    @Id
    private String id;
    private String name;
    private String description;
    private List<BlogPost> posts;
    private LocalDateTime createdAt;

}
