package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@With
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode(of = "image_id")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID image_id;

  @Column(nullable = false)
  private String filename;

  @Column(nullable = false)
  private String url;

  @Column(nullable = false)
  private String mime_type;

  private Long size;
  private String description;
  private Integer width;
  private Integer height;
}
