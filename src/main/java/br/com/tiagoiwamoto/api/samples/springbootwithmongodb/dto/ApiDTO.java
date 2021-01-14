package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.dto;

/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 13:48
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ApiDTO<T> {

    private String code;
    private String message;
    private T detail;
    private LocalDateTime timestamp;
    private String protocol;

    public ApiDTO() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        this.protocol = UUID.nameUUIDFromBytes(dtf.format(LocalDateTime.now()).getBytes()).toString();
    }
}
