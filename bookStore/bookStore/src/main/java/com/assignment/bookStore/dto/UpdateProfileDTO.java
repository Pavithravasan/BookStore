package com.assignment.bookStore.dto;

import com.assignment.bookStore.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDTO {

    public String password;
    public String username;
    public String email;

}
