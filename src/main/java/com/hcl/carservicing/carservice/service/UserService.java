package com.hcl.carservicing.carservice.service;

import com.hcl.carservicing.carservice.model.AppUser;

public interface UserService {
    /**
     * Registers a new user. 
     * @param user AppUser entity to register
     * @return persisted AppUser
     */
    AppUser register(AppUser user);

    /**
     * Authenticates user credentials. 
     * @param userId user's login ID
     * @param password user's password
     * @return authenticated AppUser
     */
    AppUser login(String userId, String password);
}
