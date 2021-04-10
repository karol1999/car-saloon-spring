package com.example.lab9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // używamy do oznaczenia klasy konfiguracyjnej która deklaruje
                       // jednego lub więcej Beanów oraz uruchamia auto-konfiguracje oraz
                       // skanowane komponentów
public class Lab
{
    public static void main(String[] args) {
        SpringApplication.run(Lab.class, args);
    }
}
