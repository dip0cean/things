package com.things.project02.controller.user;

import com.things.project02.repository.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserApiControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void 회원가입() {
        String requestBody =
                "{\n" +
                        "\"userId\": \"testId_1234\",\n" +
                        "\"userPw\": \"testPw_1234\",\n" +
                        "\"userNick\": \"testNick_1234\",\n" +
                        "\"userIntro\": \"Hello World!\"\n" +
                        "}";
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when().post("/api/signup")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void 회원상세페이지() {
        회원가입();
        RestAssured.given().log().all()
                .when().get("/user/detail/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}