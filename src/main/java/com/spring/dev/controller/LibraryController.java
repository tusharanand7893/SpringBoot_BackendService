package com.spring.dev.controller;

import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.entities.AddBookResponse;
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

    @PostMapping("/api/addBook")
    public ResponseEntity addBookInLibrary(@RequestBody LibraryDao libraryDao){
        libraryDao.setId(libraryDao.getIsbn()+libraryDao.getAisle());
        libraryRepository.save(libraryDao);
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("UniqueId",libraryDao.getIsbn()+"Tushar$");
        addBookResponse=addBookResponse.successMessage(libraryDao.getIsbn()+libraryDao.getAisle());
        return new ResponseEntity<AddBookResponse>(addBookResponse,httpHeaders,HttpStatus.CREATED);

    }
}
