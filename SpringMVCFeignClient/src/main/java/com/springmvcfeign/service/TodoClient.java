package com.springmvcfeign.service;

import com.springmvcfeign.pojo.Todo;
import feign.RequestLine;

public interface TodoClient {
    @RequestLine("GET /todos/1")
    Todo getTodo();
}
