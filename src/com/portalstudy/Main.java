package com.portalstudy;

import com.portalstudy.connection.ConnectionFactory;
import com.portalstudy.model.User;
import com.portalstudy.repository.UserRepository;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> userList = findAll();
        System.out.println(userList);
    }

    public static void save(){
        User user = new User("João da Silva","123","STUDENT");
        UserRepository.save(user);
    }

    public static void update(){
        User user = new User(2L,"João das Flores","123","STUDENT");

        UserRepository.update(user);
    }

    public static void delete(){
        User user = new User();
        user.setUser_id(2L);
        UserRepository.delete(user);
    }

    public static List<User> findAll(){
        return UserRepository.findAll();
    }
}
