package pl.kamil.artsiteandstoreapi.application.port;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@DBRider
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaceRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5");

    @Autowired
    private PlaceRepository pr;

    @Test
    void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    @DataSet(value = {"datasets/places.json", "datasets/pages.json"}, cleanBefore = true)
    @DisplayName("tests whether placeIds are fetched properly")
    public void shouldReturnIdsOfPlacesAsList() {
        // given
        Integer pageId = 1;
        // when
        List<UUID> result = pr.findAllPlaceIdByPage_PageId(pageId);
        // then
        int expected = 3;
        List<UUID> expectedIdsList = List.of(
                UUID.fromString("54adf1b6-10b9-41f6-aa55-38ec396505da"),
                UUID.fromString("e112ed68-be19-44be-af4b-1ead42004741"),
                UUID.fromString("8baa84a1-d7ec-402e-bcbc-88ecf7421a15"));
        assertEquals(expected, result.size());
        assertThat(expectedIdsList).hasSameElementsAs(result);
    }
}