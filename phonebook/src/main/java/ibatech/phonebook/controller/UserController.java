package ibatech.phonebook.controller;

import ibatech.phonebook.dto.UserDto;
import ibatech.phonebook.model.ResponseData;
import ibatech.phonebook.model.User;
import ibatech.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("/")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("users")
    public ResponseEntity<ResponseData> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.save(userDto));
    }
    @PutMapping("users/{id}")
    public ResponseEntity<ResponseData>  editUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(service.update(id, user));
    }
    @DeleteMapping("users/{id}")
    public ResponseEntity<ResponseData>  deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
    @GetMapping("users")
    public ResponseEntity<List<User>>  getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping("users/{id}")
    public ResponseEntity<User>  getUser(@PathVariable Long id){
        return ResponseEntity.ok(service.getUser(id));
    }
}
