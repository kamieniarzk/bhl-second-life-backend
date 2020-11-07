package com.secondlife.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
public class UserProfileDTO {

    @NotEmpty(message = "Username cannot be empty.")
    private String userName;

    @NotEmpty(message = "UserLastname cannot be empty.")
    private String userLastname;

    @NotEmpty(message = "Radius cannot be empty.")
    private Double radius;

    private Set<Long> advertisements = new HashSet<>();

}
