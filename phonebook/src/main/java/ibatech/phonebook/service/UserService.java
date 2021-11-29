package ibatech.phonebook.service;

import ibatech.phonebook.dto.UserDto;
import ibatech.phonebook.model.ResponseData;
import ibatech.phonebook.model.User;
import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;
import ibatech.phonebook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseData save (UserDto userDto)
    {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        User createdUser = userRepository.save(user);
        return new ResponseData(createdUser.getUserId(), OperationType.ADD, OperationStatus.SUCCESS);
    }

    public ResponseData update (Long id, User user)
    {
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setName(user.getName());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser = userRepository.save(user);
        return new ResponseData(currentUser.getUserId(), OperationType.EDIT, OperationStatus.SUCCESS);
    }

    public ResponseData delete (Long id)
    {
        userRepository.deleteById(id);
        return new ResponseData(id, OperationType.DELETE, OperationStatus.SUCCESS);
    }

    public List<User> getAllUsers ()
    {
        return userRepository.findAll();
    }
    public User getUser (Long id)
    {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
