package com.spring.dev.SpringBoot_BackendService;

import com.spring.dev.controller.LibraryController;
import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.entities.AddBookResponse;
import com.spring.dev.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootBackendServiceApplicationTests {

	@Autowired
	LibraryService libraryService;

	@Autowired
	LibraryController libraryController;

	@MockBean
	LibraryService libraryServiceMock;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkGenerateIdLibraryService(){
		String id=libraryService.generateId("Test",123);
		assertEquals("Test123",id);
		String id1=libraryService.generateId("NTest",123);
		assertEquals("NEW_WRITERNTest123",id1);
		String id2=libraryService.generateId("ZTest",123);
		assertEquals("OLD_WRITERZTest123",id2);
	}

	@Test
	public void checkaddBookInLibraryController(){

		when(libraryServiceMock.generateId("",0)).thenReturn("test");
		when(libraryServiceMock.checkIfBookAlreadyExist("test")).thenReturn(true);
		ResponseEntity response=libraryController.addBookInLibrary(buildLibraryDao());
		AddBookResponse ad= (AddBookResponse) response.getBody();
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
		assertEquals("Book with id test Already present",ad.getMessage());
	}



	public LibraryDao buildLibraryDao(){
		return LibraryDao.builder()
				.aisle(0)
				.isbn("")
				.bookName("")
				.author("")
				.id("")
				.build();
	}

}
