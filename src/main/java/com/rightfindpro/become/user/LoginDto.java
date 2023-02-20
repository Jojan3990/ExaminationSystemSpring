package com.rightfindpro.become.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class LoginDto{
//    private static final long serialVersionUID = -3558537416135446309L;
    private String username;
    private String password;
}
