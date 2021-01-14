package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.entity;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:43
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAddress {

    private String address;
    private String cep;
    private String description;

}
