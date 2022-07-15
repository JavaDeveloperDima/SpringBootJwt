package com.example.springrestjwt.repositories;

import com.example.springrestjwt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "select * from massage order by id limit 10",nativeQuery = true)
    List<String> massageList();
}
