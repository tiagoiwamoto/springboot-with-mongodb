package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:42
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPhone {

    private String phone;
    private String description;

}
