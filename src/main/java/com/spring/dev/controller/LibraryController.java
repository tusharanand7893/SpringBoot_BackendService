package com.spring.dev.controller;

import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.entities.AddBookResponse;
import com.spring.dev.service.LibraryService;
import com.spring.dev.utility.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    AddBookResponse addBookResponse;

    @Autowired
    LibraryService libraryService;

    @PostMapping("/api/addBook")
    public ResponseEntity addBookInLibrary(@RequestBody LibraryDao libraryDao){
        String id= libraryService.generateId(libraryDao.getIsbn(),libraryDao.getAisle());
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("UniqueId",libraryDao.getIsbn()+"Tushar$");
        HttpStatus httpStatus;
        if(!libraryService.checkIfBookAlreadyExist(id)){
            libraryDao.setId(id);
            libraryRepository.save(libraryDao);
            addBookResponse=addBookResponse.successMessage(id);
           httpStatus=HttpStatus.CREATED;
        }
        else {
            addBookResponse = addBookResponse.bookExistingMessage(id);
            httpStatus = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<AddBookResponse>(addBookResponse,httpHeaders,httpStatus);

    }
}
