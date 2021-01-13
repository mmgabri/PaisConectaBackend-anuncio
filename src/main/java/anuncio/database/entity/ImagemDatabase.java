package anuncio.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="imagem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagemDatabase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataHoraCriacao;
    private String uri;

    @ManyToOne
    @JoinColumn(name="anuncio_id")
    private AnuncioDatabase anuncio;
}
