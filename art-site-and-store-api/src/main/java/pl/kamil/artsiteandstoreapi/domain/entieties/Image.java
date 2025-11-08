package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;
import pl.kamil.artsiteandstoreapi.domain.dtos.FileName;

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
}
