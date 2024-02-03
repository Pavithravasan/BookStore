package com.assignment.bookStore.dto;

import com.assignment.bookStore.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    public String password;
    public String username;
    public Role role;
    public String email;

}
