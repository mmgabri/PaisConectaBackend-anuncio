package anuncio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import anuncio.domain.enums.CategoriaEnum;
import anuncio.domain.enums.StatusAnuncioEnum;
import anuncio.domain.enums.TipoAnuncioEnum;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDto {
    private Integer id;
    private String titulo;
    private String descricao;
    private CategoriaEnum categoria;
    private TipoAnuncioEnum tipo;
    private double valor;
    private String cep;
    private String idUsuario;

    private StatusAnuncioEnum status;
    private String dataHoraAnuncio;
    private List<String> imagens = new ArrayList<>();
}
