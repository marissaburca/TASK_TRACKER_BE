package marissaburca.TASK_TRACKER_BE;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import marissaburca.TASK_TRACKER_BE.entities.Gender;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserLoginDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTest {
    @Autowired
    private ObjectMapper objectMapper;
    static Faker faker = new Faker();

    private static String authToken;

    private  static String email;

    private static String password = "akskfeirtbldf";

    @BeforeAll
    public static void setUpEmail() {
        email = faker.name().username()+"@"+faker.internet().domainName();
    }

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:3001";
    }
    @Test
    @Order(1)
    void register() throws JsonProcessingException {


        String requestBody = objectMapper.writeValueAsString(
                new UserDTO(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(), Gender.FEMALE, 1L,email,
                        password
                ));

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/auth/register");
        response.then().assertThat().statusCode(201);
    }
    @Test
    @Order(2)
    void registerFailed() throws JsonProcessingException {


        String requestBody = objectMapper.writeValueAsString(
                new UserDTO(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.name().username(), Gender.MALE, 1L,"",
                        password
                ));

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/auth/register");
        response.then().assertThat().statusCode(400);
    }

    @Test
    @Order(3)
    void login() throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(
                new UserLoginDTO(email,
                        password));

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/auth/login");
        response.then().assertThat().statusCode(200);
        JsonNode jsonNode = objectMapper.readTree(response.body().asString());
        authToken = jsonNode.get("token").toString();
    }
    @Test
    @Order(4)
    void tokenOk() throws JsonProcessingException {
        assertNotNull(authToken);
    }

}
