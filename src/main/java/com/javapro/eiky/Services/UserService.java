package com.javapro.eiky.Services;

import com.javapro.eiky.Models.user.User;

public interface UserService {
    public abstract User getUser(Long id);
    public abstract User saveUser(User usr);
    public abstract void deleteUser(Long id);
    public abstract Iterable<User> getAllUsers();
}
