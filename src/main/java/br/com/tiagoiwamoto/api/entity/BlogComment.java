package br.com.tiagoiwamoto.api.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/05/2021 | 10:20
 */

import br.com.tiagoiwamoto.api.model.dto.BlogUserDTO;
import lombok.Data;

@Data
public class BlogComment {

    private BlogUserDTO blogUser;
    private String comment;

}
