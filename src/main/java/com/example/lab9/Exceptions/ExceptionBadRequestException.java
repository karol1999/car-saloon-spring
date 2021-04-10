package com.example.lab9.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStataus oznacza metodę lub klasę wyjątku kodem oraz wiadomością zwrotną, która
// powinna być zwrócona. Kod jest zaaplikowany do HTTP kiedy metoda obsługująca wyjątek jest
// wywołana albo kiedykolwiek zostanie rzucony ten wyjątek.

// HttpStatus.BAD_REQUEST == 400 Bad Request

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionBadRequestException extends RuntimeException
{
    public ExceptionBadRequestException(String newMessage)
    {
        super(newMessage);
    }
}

