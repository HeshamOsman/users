package com.gamary.orange.service.impl;

import com.gamary.orange.domain.User;
import com.gamary.orange.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUsers(){
        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<User>(Arrays.asList(new User(1L,"Admin",true))));

        Page<User> users = userService.getUsers(new PageableDefaults());

        assertThat(users.getContent()).hasSize(1);

    }



    @Test
    public void testCreateUser(){
        User u = new User(null,"Admin",true) ;
        when(userRepository.save(any())).thenReturn(new User(1L,"Admin",true));

        User user = userService.createOrUpdateUser(u);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Admin");
    }

    @Test
    public void testUpdateUser(){
        User u = new User(1L,"Admin1",true) ;
        when(userRepository.save(any())).thenReturn(u);
        when(userRepository.existsById(any())).thenReturn(true);

        User user = userService.createOrUpdateUser(u);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Admin1");
    }

    class PageableDefaults implements Pageable{


        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 10;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return Sort.unsorted();
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    }
}
