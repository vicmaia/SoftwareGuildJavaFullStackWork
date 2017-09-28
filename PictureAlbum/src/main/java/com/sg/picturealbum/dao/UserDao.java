package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.User;
import java.util.List;

public interface UserDao {

 public User addUser(User newUser);

 public void deleteUser(String username);
 
 public List<User> getAllUsers();

}
