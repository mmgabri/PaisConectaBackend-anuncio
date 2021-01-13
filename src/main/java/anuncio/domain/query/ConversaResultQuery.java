package anuncio.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConversaResultQuery {
    private Integer id;
//    private Integer idAnuncio;
//    private Integer idUsuarioDe;
//    private Integer idUsuarioPara;
//    private Boolean usuarioParaRead;
    private String mensagem;
//    @JsonIgnore
//    private AnuncioDatabase anuncio;
//    private LocalDateTime dataHoraCriacao;

}
