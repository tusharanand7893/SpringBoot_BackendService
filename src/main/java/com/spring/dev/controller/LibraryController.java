package com.spring.dev.controller;

import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.entities.AddBookResponse;
import com.spring.dev.service.LibraryService;
import com.spring.dev.utility.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping("/api/getBookById/{id}")
    public LibraryDao getBookById(@PathVariable(value = "id") String id){
        try {
            return libraryRepository.findById(id).get();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/getAllBooks")
    public List<LibraryDao> getAllBooks(){
        return libraryRepository.findAll();
    }

    @GetMapping("/api/getBookByAuthor")
    public List<LibraryDao> getBooksByAuthor(@RequestParam(value="authorName") String authorName ){
        return libraryRepository.findBooksByAuthor(authorName);
    }

    @PutMapping("/api/updateBookById/{id}")
    public ResponseEntity<LibraryDao> updateBookById(@PathVariable(value = "id") String id, @RequestBody LibraryDao libraryDao){
     try{
         LibraryDao existingBook=libraryRepository.findById(id).get();
         existingBook.setBookName(libraryDao.getBookName());
         existingBook.setAuthor(libraryDao.getAuthor());
         libraryRepository.save(existingBook);
         return new ResponseEntity<LibraryDao>(existingBook,HttpStatus.CREATED);
     }catch (Exception e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
     }
    }

    @DeleteMapping("/api/deleteBookById")
    public ResponseEntity<String> deleteBookById(@RequestBody LibraryDao libraryDao){
           if( libraryService.checkIfBookAlreadyExist(libraryDao.getId())){
               LibraryDao book = libraryRepository.findById(libraryDao.getId()).get();
               libraryRepository.delete(book);
               return new ResponseEntity<>("Book is Deleted", HttpStatus.OK);
           }
            else{
            return new ResponseEntity<>("No Book Present",HttpStatus.NOT_FOUND);
        }


    }


}
