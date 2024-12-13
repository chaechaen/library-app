package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.requestDTO.UserCreateRequestDTO;
import com.group.libraryapp.dto.user.requestDTO.UserUpdateRequestDTO;
import com.group.libraryapp.dto.user.responseDTO.UserResponseDTO;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequestDTO requestDTO) {
        User u = userRepository.save(new User(requestDTO.getName(), requestDTO.getAge()));
    }

    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getId())
                .orElseThrow(IllegalArgumentException::new); // User가 없는 경우 예외 던짐

        user.updateName(requestDTO.getName()); // 이름 변경 (객체 업데이트)
        userRepository.save(user);
    }

    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }

}
