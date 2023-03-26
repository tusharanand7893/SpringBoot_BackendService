package com.spring.dev.service;

import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.utility.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    public String generateId(String isbn, int aisle){
        if(isbn.startsWith("Z")){
            return "OLD_WRITER"+isbn+aisle;
        }
        else if(isbn.startsWith("N")){
            return "NEW_WRITER"+isbn+aisle;
        }
        return isbn+aisle;
    }

    public boolean checkIfBookAlreadyExist(String id){
        Optional<LibraryDao> libraryDao=libraryRepository.findById(id);
        if(libraryDao.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
