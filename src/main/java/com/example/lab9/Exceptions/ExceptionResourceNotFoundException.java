package com.example.lab9.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStataus oznacza metodę lub klasę wyjątku kodem oraz wiadomością zwrotną, która
// powinna być zwrócona. Kod jest zaaplikowany do HTTP kiedy metoda obsługująca wyjątek jest
// wywołana albo kiedykolwiek zostanie rzucony ten wyjątek.

// HttpStatus.NOT_FOUND == 404 Not Found

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionResourceNotFoundException extends RuntimeException
{
    public ExceptionResourceNotFoundException(String newMeesage)
    {
        super(newMeesage);
    }
}



