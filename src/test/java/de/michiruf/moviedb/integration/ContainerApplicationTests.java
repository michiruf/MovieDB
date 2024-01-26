package de.michiruf.moviedb.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;

//@SpringBootTest
//@Testcontainers
public class ContainerApplicationTests {

    @Test
    void shouldGetAllMovies() {
        var dockerfileImage = new ImageFromDockerfile().withDockerfile(Path.of("./Dockerfile"));
        try (var container = new GenericContainer<>(dockerfileImage)) {
            container.start();

            RestAssured.given()
                    .basePath("http://" + container.getHost())
                    .port(container.getFirstMappedPort())
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/api/movie/")
                    .then()
                    .statusCode(200)
                    .body(".", Matchers.hasSize(2));
        }
    }
}
