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

    public AddBookResponse bookExistingMessage(String id){
        AddBookResponse addBookResponse= new AddBookResponse();
        addBookResponse.setMessage("Book with id "+id+" Already present");
        return addBookResponse;
    }

    public AddBookResponse bookUpdatedSuccessfully(String id){
        AddBookResponse addBookResponse= new AddBookResponse();
        addBookResponse.setMessage("Book Updated Successfully");
        addBookResponse.setId(id);
        return addBookResponse;
    }
}
