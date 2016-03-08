package com.redhat.consulting.fusequickstars.eap.rest;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;

/**
 *   Users storage for testing the Rest CRUD functionality.
 *
 *   It's basic, doesn't check duplicates id's and in case of update, updates the first found.
 *
 */

/**
 *  @author lberetta
 *
 */

@Singleton
@Named("usersBean")
public class UsersBean {

    ArrayList<User> users = new ArrayList<User>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(Integer id) {
        for (User user:users)
            if (user.getId().equals(id))
                return user;

        return null;
    }

    public User createUser(User user) {
        users.add(user);

        return user;
    }

    public User updateUser(User userToUpdate) {
        for (User user:users)
            if (user.getId().equals(userToUpdate.getId())) {
                user.setUsername(userToUpdate.getUsername());

                return user;
            }

        return null;
    }

    public boolean deleteUser(Integer id) {
        for (User user:users)
            if (user.getId().equals(id)) {
                return users.remove(user);
            }

        return false;
    }
}
