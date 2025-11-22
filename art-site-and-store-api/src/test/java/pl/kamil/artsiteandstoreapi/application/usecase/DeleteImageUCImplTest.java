package pl.kamil.artsiteandstoreapi.application.usecase;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kamil.artsiteandstoreapi.configuration.TestSecurityConfig;
import pl.kamil.artsiteandstoreapi.domain.ports.ImageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestSecurityConfig.class)
@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@DBRider
@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeleteImageUCImplTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5");

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ImageRepository ir;

    @Test
    void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    @DataSet(value = {"datasets/images.json", "datasets/places.json", "datasets/pages.json"}, cleanBefore = true)
    @DisplayName("tests whether after deletion images.json size decreases by 1")
    void shouldDeleteImageProperly() {
        // given
        var url = "/api/main-page/f3180420-c8ac-4e3b-a8da-ffcfa78f03d7";
        // when
        ResponseEntity<HttpStatus> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                HttpStatus.class);
        // then
        int expected = 2;
        int actual = ir.findAll().size();
        HttpStatus OK = HttpStatus.OK;

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(actual).isEqualTo(expected);
    }
}