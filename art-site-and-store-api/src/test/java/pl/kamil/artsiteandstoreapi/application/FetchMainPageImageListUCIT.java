package pl.kamil.artsiteandstoreapi.application;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kamil.artsiteandstoreapi.application.port.ImageRepository;
import pl.kamil.artsiteandstoreapi.application.port.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@DBRider
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FetchMainPageImageListUCIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5");

    @Autowired
    private PlaceRepository pr;

    @Autowired
    private ImageRepository ir;
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    @DataSet(value = {"datasets/images.json", "datasets/places.json", "datasets/pages.json"}, cleanBefore = true)
    public void printImages() {
        List<Place> places = placeRepository.findAll();
        System.out.println("Places: " + places);
        assertEquals(3, places.size());
    }
}
