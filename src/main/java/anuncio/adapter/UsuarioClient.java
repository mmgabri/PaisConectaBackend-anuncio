package anuncio.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import anuncio.domain.dto.UsuarioDto;

@FeignClient(name = "usuario", url = "http://localhost:9090/usuarios")
public interface UsuarioClient {

    @RequestMapping("{id}")
    UsuarioDto get(@PathVariable("id") String id);
}
