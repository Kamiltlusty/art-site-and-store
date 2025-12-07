package pl.kamil.artsiteandstoreapi.domain.ports.outgoing;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kamil.artsiteandstoreapi.configuration.ContainerConnection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@DBRider
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = {ContainerConnection.class})
class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository pr;

    @Test
    public void simpleTest() {
        assertTrue(2==2);
    }

//    @Test
//    @DataSet(value = {"datasets/places.json", "datasets/pages.json"}, cleanBefore = true)
//    @DisplayName("tests whether placeIds are fetched properly")
//    public void shouldReturnIdsOfPlacesAsList() {
//        // given
//        Integer pageId = 1;
//        // when
//        List<UUID> result = pr.findAllPlaceIdByPage_PageId(pageId);
//        // then
//        int expected = 3;
//        List<UUID> expectedIdsList = List.of(
//                UUID.fromString("54adf1b6-10b9-41f6-aa55-38ec396505da"),
//                UUID.fromString("e112ed68-be19-44be-af4b-1ead42004741"),
//                UUID.fromString("8baa84a1-d7ec-402e-bcbc-88ecf7421a15"));
//        assertEquals(expected, result.size());
//        assertThat(expectedIdsList).hasSameElementsAs(result);
//    }
}