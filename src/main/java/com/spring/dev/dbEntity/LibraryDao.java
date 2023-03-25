package com.spring.dev.dbEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name="lib_store")
public class LibraryDao {

    @Id
    @Column(name="id")
    private String id;
    @Column(name="book_name")
    private String bookName;
    @Column(name="aisle")
    private int aisle;
    @Column(name="author")
    private String author;
    @Column(name="isbn")
    private String isbn;
}
