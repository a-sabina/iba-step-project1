package ibatech.phonebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibatech.phonebook.dto.UserDto;
import ibatech.phonebook.model.ResponseData;
import ibatech.phonebook.model.User;
import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;
import ibatech.phonebook.repository.UserRepository;
import ibatech.phonebook.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({UserController.class})
class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser() throws Exception {

        when(userService.save(any())).thenReturn(new ResponseData(1L, OperationType.ADD, OperationStatus.SUCCESS));
        UserDto userDto = new UserDto("test", "123");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .content(asJsonString(userDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void editUser() throws Exception {
        when(userService.save(any())).thenReturn(new ResponseData(1L, OperationType.EDIT, OperationStatus.SUCCESS));
        User user = createMockUser(1L, "test", "123");

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/users/1")
                                .content(asJsonString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        when(userService.delete(any())).thenReturn(new ResponseData(1L, OperationType.DELETE, OperationStatus.SUCCESS));

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(createListOfMockUsers());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(createMockUser(4L, "Test", "123")));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    private List<User> createListOfMockUsers() {
        return Arrays.asList(
                new User(1L, "Jim", "2546119551"),
                new User(2L, "Tom", "3576608599"),
                new User(3L, "Joseph", "2638804936"),
                new User(4L, "Michael", "3259608826"),
                new User(5L, "Olena", "3241113539")
        );
    }

    private User createMockUser(Long id, String name, String phoneNumber) {
        return User.builder()
                .userId(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}