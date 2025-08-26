package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@With
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode(of = "place_id")
@NoArgsConstructor
@AllArgsConstructor
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID place_id;

  @Column(nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(
    name = "place_image",
    joinColumns = @JoinColumn(name = "place_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id")
  )
  private List<Image> images;
}
