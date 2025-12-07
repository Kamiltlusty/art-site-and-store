package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@With
@Table(name = "image")
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode(of = "imageId")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  @Id
  @Column(name = "image_id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID imageId;

  @Column(nullable = false, name = "file_name")
  private String fileName;

  @Column(nullable = false)
  private String path;

  @Column(nullable = false, name = "mime_type")
  private String mimeType;

  private Long size;
  private String description;
  private Integer width;
  private Integer height;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;
}
