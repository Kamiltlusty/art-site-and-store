package pl.kamil.artsiteandstoreapi.application.usecase;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@DBRider
@Profile("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FetchMainPageImageListUCIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    @DataSet(value = {"datasets/images.json", "datasets/places.json", "datasets/pages.json"}, cleanBefore = true)
    @DisplayName("testing full path from main page controller through domain and database")
    public void shouldProperlyProcessRequestAndReturnImageDTO() {
        // given
        var url = "/api/main-page";
        // when
        ResponseEntity<List<ImageDTO>> actual = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        // then
        assertThat(expected).hasSameElementsAs(actual.getBody());
    }

    private static final List<ImageDTO> expected = List.of(
            ImageDTO.builder()
                    .imageId(UUID.fromString("f3180420-c8ac-4e3b-a8da-ffcfa78f03d7"))
                    .fileName("obrazek1")
                    .path( "static/obrazek1.jpeg")
                    .mimeType("image/jpeg")
                    .size(1000L)
                    .description("opis")
                    .width(50).height(100)
                    .build(),
            ImageDTO.builder()
                    .imageId(UUID.fromString("b955eaa3-3c29-42e6-aeca-8280d6862986"))
                    .fileName("obrazek2")
                    .path("static/obrazek2.jpeg")
                    .mimeType("image/jpeg")
                    .size(1000L)
                    .description("opis")
                    .width(50).height(100)
                    .build(),
            ImageDTO.builder()
                    .imageId(UUID.fromString("76955d8a-46f7-486b-8f24-c6bbea8051b5"))
                    .fileName("obrazek3")
                    .path("static/obrazek3.jpeg")
                    .mimeType("image/jpeg")
                    .size(1000L)
                    .description("opis")
                    .width(50).height(100)
                    .build()
    );
}
