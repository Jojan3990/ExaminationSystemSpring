package com.rightfindpro.become.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter


@Setter
public class UpdateUserRequest {
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    private String name;

}
