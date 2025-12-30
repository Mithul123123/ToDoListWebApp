package com.example.todolist.repository;

//import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;

import com.example.todolist.model.Document1;

import java.util.List;


public interface MyRepository extends MongoRepository<Document1,String> 
{
    void deleteByTtasknum(int ttasknum);
    Document1 findByTtasknum(int ttasknum);
    List<Document1> findByDemail(String demail);
}
