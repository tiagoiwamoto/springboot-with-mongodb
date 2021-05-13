package br.com.tiagoiwamoto.api.exception;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 12/05/2021 | 16:50
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Não conseguimos concluír sua solicitação, tente novamente mais tarde")
public class PostRecoverException extends RuntimeException{
}
