package pl.kamil.artsiteandstoreapi.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
