package anuncio.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import anuncio.domain.enums.CategoriaEnum;
import anuncio.domain.enums.StatusAnuncioEnum;
import anuncio.domain.enums.TipoAnuncioEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="anuncio")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDatabase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private TipoAnuncioEnum tipo;
    private CategoriaEnum categoria;
    private String valor;
    private String cep;
    private String idUsuario;
    private StatusAnuncioEnum status;
    private LocalDateTime dataHoraCriacao;
    @OneToMany(mappedBy="anuncio", cascade= CascadeType.ALL)
    private List<ImagemDatabase> imagens = new ArrayList<>();

}
