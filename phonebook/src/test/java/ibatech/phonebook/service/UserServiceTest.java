package ibatech.phonebook.service;

import ibatech.phonebook.conf.BaseTest;
import ibatech.phonebook.dto.UserDto;
import ibatech.phonebook.model.ResponseData;
import ibatech.phonebook.model.User;
import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        UserDto userDto = new UserDto("Mark", "099873654");
        ResponseData savedUserResponse = userService.save(userDto);

        assertThat(savedUserResponse).matches(u -> u.getOperationType().equals(OperationType.ADD))
                .matches(u -> u.getOperationStatus().equals(OperationStatus.SUCCESS));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void delete() {
        ResponseData deletedUserResponse = userService.delete(1L);
        assertThat(deletedUserResponse).matches( u -> u.getOperationType().equals(OperationType.DELETE))
                                       .matches( u -> u.getOperationStatus().equals(OperationStatus.SUCCESS));

    }

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        assertEquals(5, users.size());
    }

    @Test
    void getUser() {
        User user = userService.getUser(4L);
        assertEquals("Michael", user.getName());
    }
}