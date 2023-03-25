package com.spring.dev.utility;

import com.spring.dev.dbEntity.LibraryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryDao,String>,LibraryDataCustom  {
}
