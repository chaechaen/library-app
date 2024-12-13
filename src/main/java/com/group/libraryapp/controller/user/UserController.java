package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.requestDTO.UserCreateRequestDTO;
import com.group.libraryapp.dto.user.requestDTO.UserUpdateRequestDTO;
import com.group.libraryapp.dto.user.responseDTO.UserResponseDTO;
import com.group.libraryapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequestDTO requestDTO) {
        userService.saveUser(requestDTO);
    }

    @GetMapping("/user")
    public List<UserResponseDTO> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        userService.updateUser(requestDTO);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}
