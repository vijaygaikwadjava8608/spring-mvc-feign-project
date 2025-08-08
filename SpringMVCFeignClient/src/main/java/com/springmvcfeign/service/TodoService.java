package com.springmvcfeign.service;

import com.springmvcfeign.pojo.Todo;
import feign.Feign;
import feign.jackson.JacksonDecoder;

public class TodoService {

    private TodoClient todoClient;

    public TodoService() {
        this.todoClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(TodoClient.class, "https://jsonplaceholder.typicode.com");
    }

    public Todo getTodo() {
        return todoClient.getTodo();
    }
}
