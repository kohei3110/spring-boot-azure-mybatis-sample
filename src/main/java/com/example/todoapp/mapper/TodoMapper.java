package com.example.todoapp.mapper;

import com.example.todoapp.domain.Todo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<Todo> findAll();

    Todo findById(@Param("id") Long id);

    int insert(Todo todo);

    int update(Todo todo);

    int deleteById(@Param("id") Long id);
}
