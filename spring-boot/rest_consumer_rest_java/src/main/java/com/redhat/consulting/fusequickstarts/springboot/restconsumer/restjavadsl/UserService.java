package com.redhat.consulting.fusequickstarts.springboot.restconsumer.restjavadsl;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private ConcurrentHashMap<Integer, User> usersById = new ConcurrentHashMap<>();

    // Expose the internal user list for testing
    Map<Integer, User> getUsersById() {
        return usersById;
    }

    public Collection<User> getUsers() {
        return usersById.values();
    }

    public User getUser(int id) {
        return usersById.get(id);
    }

    public User createUser(User createUser) {
        usersById.put(createUser.getId(), createUser);
        return createUser;
    }

    public User updateUser(User updateUser) {
        usersById.put(updateUser.getId(), updateUser);
        return updateUser;
    }

    public boolean deleteUser(int id) {
        User deleted = usersById.remove(id);
        return deleted != null;
    }

}
