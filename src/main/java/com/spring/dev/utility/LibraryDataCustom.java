package com.spring.dev.utility;

import com.spring.dev.dbEntity.LibraryDao;

import java.util.List;

public interface LibraryDataCustom {

    public List<LibraryDao> findBooksByAuthor(String authorName);
}
