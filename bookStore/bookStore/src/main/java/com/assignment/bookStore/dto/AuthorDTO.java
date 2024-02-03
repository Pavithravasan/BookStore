package com.assignment.bookStore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthorDTO {
    @NotNull
    private Long authorId;
    private  String name;
    private  String details;

}
