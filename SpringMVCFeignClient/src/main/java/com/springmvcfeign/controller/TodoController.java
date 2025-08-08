package com.springmvcfeign.controller;

import com.springmvcfeign.pojo.Todo;
import com.springmvcfeign.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    private TodoService todoService = new TodoService();

    @GetMapping(value = "/feign-todo" , produces = "application/json")
    public Todo getTodo() {
        return todoService.getTodo();
    }
}
