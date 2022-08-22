package com.rightfindpro.become.mapper;

import com.rightfindpro.become.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private  UserService userService;
}
