package ibatech.phonebook.repository;

import ibatech.phonebook.conf.BaseTest;
import ibatech.phonebook.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRepositoryTest extends BaseTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testSave() {
        User userToSave = new User(6L, "Sabina", "55676543");
        userRepository.save(userToSave);
        List<User> users = userRepository.findAll();
        assertThat(users).matches(u -> u.size() == 6);
    }

    @Test
    void testUpdate() {
        User userToUpdate = new User(1L, "JimUpdated", "1234567");
        User updatedUser = userRepository.save(userToUpdate);
        assertThat(updatedUser).matches(u -> u.getName().equals("JimUpdated"));
    }

    @Test
    void testDelete() {
        userRepository.deleteById(1L);
        Optional<User> user = userRepository.findById(1L);
        assertThat(user).matches(u -> !u.isPresent());
    }

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertThat(users).matches(u -> u.size() == 5);
    }

    @Test
    void testFindById() {
        Optional<User> user = userRepository.findById(4L);
        assertThat(user).matches(u -> u.get().getName().equals("Michael"));
    }
}