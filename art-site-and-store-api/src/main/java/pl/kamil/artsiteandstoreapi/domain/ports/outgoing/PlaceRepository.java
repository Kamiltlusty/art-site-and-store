package pl.kamil.artsiteandstoreapi.domain.ports.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kamil.artsiteandstoreapi.domain.entieties.Page;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place,UUID> {
    List<Place> findAllByPage_PageId(Integer pagePageId);
}
