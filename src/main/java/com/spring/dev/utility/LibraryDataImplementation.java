package com.spring.dev.utility;

import com.spring.dev.dbEntity.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LibraryDataImplementation implements LibraryDataCustom{

    @Autowired
    LibraryRepository libraryRepository;
    @Override
    public List<LibraryDao> findBooksByAuthor(String authorName) {
        List<LibraryDao> libraryDaos= libraryRepository.findAll();
        List<LibraryDao> matchingAuthorBooks= new ArrayList<LibraryDao>();
        for(LibraryDao l:libraryDaos){
            if(l.getAuthor().equalsIgnoreCase(authorName)){
               matchingAuthorBooks.add(l);
            }
        }
        return matchingAuthorBooks;
    }
}
