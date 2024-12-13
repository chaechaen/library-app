package com.group.libraryapp.dto.user.responseDTO;

import com.group.libraryapp.domain.user.User;

public class UserResponseDTO {

    private long id;
    private String name;
    private Integer age;


    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
