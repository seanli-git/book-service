package com.jeffrey.springboot;

import com.jeffrey.springboot.mongodb.Mbook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringBootBootstrapLiveTest {

    private static final String API_ROOT
            = "http://localhost:8081/api/mbooks";

    private Mbook createRandomBook() {
        Mbook book = new Mbook();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(15));
        return book;
    }

    private String createBookAsUri(Mbook book) {
        Response response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }

    @Test
    public void createMBook() {
        Mbook m = new Mbook("1", "2");
        System.out.println(m.toString());
    }
    @Test
    public void whenGetAllBooks_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        response.prettyPrint();
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCreatedBookById_thenOK() {
        try {
            final Mbook book = createRandomBook();
            final String location = createBookAsUri(book);

            final Response response = RestAssured.get(location);
            assertEquals(HttpStatus.OK.value(), response.getStatusCode());
            assertEquals(book.getTitle(), response.jsonPath()
                    .get("title"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
