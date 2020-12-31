package com.things.project02.controller.user;

import com.things.project02.dto.UserDto;
import com.things.project02.repository.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;

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
    private UserRepository  userRepository;
    private MockHttpSession session;

    @Test
    void 회원가입() {
        for (int i = 1; i <= 1; i++) {
            String requestBody =
                    "{\n" +
                            "\"userId\": \"testId_1234_" + i + "\",\n" +
                            "\"userPw\": \"test\",\n" +
                            "\"userNick\": \"testNick_1234_" + i + "\",\n" +
                            "\"userIntro\": \"Hello World!\"\n" +
                            "}";
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .body(requestBody)
                    .when().post("/api/user/signup")
                    .then().log().all()
                    .statusCode(HttpStatus.OK.value());
        }
        UserDto.UserRes userRes = userRepository.findById((1L)).map(UserDto.UserRes::new).orElse(null);
        session.setAttribute("userSession", userRes);
        System.out.println(userRes.getUserId());
    }

    @Test
    void 게시글작성() {

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