package com.spring.dev.entities;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class AddBookResponse {
    private String message;
    private String id;


    public AddBookResponse successMessage(String id){
        AddBookResponse addBookResponse= new AddBookResponse();
        addBookResponse.setMessage("Book Added Successfully");
        addBookResponse.setId(id);
        return addBookResponse;
    }
}
