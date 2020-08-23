package com.gamary.orange.service;

import com.gamary.orange.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createOrUpdateUser(User user);
    Page<User> getUsers(Pageable pageable);

}
