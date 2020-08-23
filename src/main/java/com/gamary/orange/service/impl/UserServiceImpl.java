package com.gamary.orange.service.impl;

import com.gamary.orange.domain.User;
import com.gamary.orange.repository.UserRepository;
import com.gamary.orange.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createOrUpdateUser(@NotNull User user) {
        log.info("Service Call: Create or update user with {}",user);
        if(user.getId()!=null&&!userRepository.existsById(user.getId())){
            throw new RuntimeException();
        }

        return userRepository.save(user);

    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        log.info("Service Call: Get users with {}",pageable);
        return userRepository.findAll(pageable);
    }

}
