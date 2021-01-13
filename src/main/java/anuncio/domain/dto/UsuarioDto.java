package anuncio.domain.dto;

import anuncio.domain.enums.StatusUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private URI uriImagem;
    private StatusUsuarioEnum status;
    private LocalDateTime dataHora;
}
