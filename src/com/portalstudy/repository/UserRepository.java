package com.portalstudy.repository;

import com.portalstudy.connection.ConnectionFactory;
import com.portalstudy.model.User;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    public static void save(User user){
        String sql = "INSERT INTO user (user_name, user_password, user_role) VALUES (?,?,?)";
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getUser_name());
            pst.setString(2, user.getUser_password());
            pst.setString(3, user.getUser_role());

            int executeSuccess = pst.executeUpdate();
            ConnectionFactory.close(connection, pst);

            if(executeSuccess > 0){
                System.out.println("Created User: " + user.getUser_name());
                System.out.println(executeSuccess);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(User user){
        if(user == null || user.getUser_id() == null){
            System.out.println("User not found");
            return;
        }
        String sql = "UPDATE user SET user_name=?, user_password=?, user_role=? WHERE (user_id=?)";

        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getUser_name());
            pst.setString(2, user.getUser_password());
            pst.setString(3, user.getUser_role());

            pst.setString(4,String.valueOf(user.getUser_id()));

            int executeSuccess = pst.executeUpdate();
            ConnectionFactory.close(connection, pst);

            if(executeSuccess > 0){
                System.out.println("Updated User: " + user.getUser_name());
                System.out.println(executeSuccess);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(User user){
        if(user == null || user.getUser_id() == null){
            System.out.println("User not found!");
            return;
        }
        String sql = "DELETE FROM user WHERE (user_id = ?)";
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,String.valueOf(user.getUser_id()));

            int executeSuccess = pst.executeUpdate();
            ConnectionFactory.close(connection, pst);
            if (executeSuccess > 0){
                System.out.println("Deleted user: " + user.getUser_name());
                System.out.println(executeSuccess);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<User> findAll(){
        String sql = "SELECT user_id, user_name, user_password, user_role FROM user";
        Connection connection = ConnectionFactory.getConnection();
        List<User> userList = new ArrayList<>();

        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                userList.add(new User(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            ConnectionFactory.close(connection, pst, rs);
            return userList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
