package com.gamary.orange.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamary.orange.domain.User;
import com.gamary.orange.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import javax.validation.constraints.NotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String URL = "/api/users";
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc userControllerMock;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetUsers() throws Exception {
        userControllerMock.perform(get(URL)).andExpect(status().isOk());
    }
    @Test
    public void testCreateUser() throws Exception {
        userControllerMock.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(new User(null,"Admin",true)))).andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.createOrUpdateUser(any())).thenReturn(new User(1L,"Admin",true)).thenReturn(new User(1L,"Admin1",true));
        //Create
        MvcResult createResult = userControllerMock.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(new User(null,"Admin",true)))).andDo(print()).andExpect(status().isOk()).andReturn();

        Long createdUserId = mapper.readValue(createResult.getResponse().getContentAsString(),User.class).getId();

        //Update
        userControllerMock.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(new User(createdUserId,"Admin1",true))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUserId))
                .andExpect(jsonPath("$.name").value("Admin1"))
                .andExpect(jsonPath("$.admin").value(true));
    }


}
