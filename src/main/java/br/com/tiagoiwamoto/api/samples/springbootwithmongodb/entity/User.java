package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:37
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
@ToString
public class User {

    @Id
    private String id;
    private String fullName;
    private LocalDate birthdate;
    private SexENUM sex;
    private List<UserPhone> phones;
    private List<UserAddress> address;
    private LocalDateTime createdAt;

}
