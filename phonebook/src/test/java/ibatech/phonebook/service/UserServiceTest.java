package ibatech.phonebook.service;

import ibatech.phonebook.conf.BaseTest;
import ibatech.phonebook.dto.UserDto;
import ibatech.phonebook.model.ResponseData;
import ibatech.phonebook.model.User;
import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;
import ibatech.phonebook.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void save() {
        UserDto userDto = new UserDto("Mark", "099873654");
        ModelMapper modelMapper = new ModelMapper();
        when(userRepository.save(modelMapper.map(userDto, User.class))).thenReturn(createMockUser(1L, "Test", "123"));
        ResponseData savedUserResponse = userService.save(userDto);

        assertThat(savedUserResponse).matches(u -> u.getOperationType().equals(OperationType.ADD))
                .matches(u -> u.getOperationStatus().equals(OperationStatus.SUCCESS));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void delete() {
        doNothing().when(userRepository).deleteById(1L);
        ResponseData deletedUserResponse = userService.delete(1L);

        assertThat(deletedUserResponse).matches(u -> u.getOperationType().equals(OperationType.DELETE))
                .matches(u -> u.getOperationStatus().equals(OperationStatus.SUCCESS));

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void update() {
        User userToUpdated = createMockUser(1L, "JimUpdated", "1234567");
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(createMockUser(1L, "Jim", "2546119551")));
        when(userRepository.save(userToUpdated)).thenReturn(userToUpdated);
        ResponseData updatedUserResponse = userService.update(1L, userToUpdated);

        assertThat(updatedUserResponse).matches(u -> u.getOperationType().equals(OperationType.EDIT))
                .matches(u -> u.getOperationStatus().equals(OperationStatus.SUCCESS));

    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(createListOfMockUsers());
        List<User> users = userService.getAllUsers();

        assertThat(users).matches(u -> u.size() == 5);
    }

    @Test
    void getUser() {
        when(userRepository.findById(4L)).thenReturn(Optional.ofNullable(createMockUser(4L, "Michael", "3259608826")));
        User user = userService.getUser(4L);

        assertThat(user).matches(u -> u.getName().equals("Michael"));
    }

    private User createMockUser(Long id, String name, String phoneNumber) {
        return User.builder()
                .userId(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
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
}
