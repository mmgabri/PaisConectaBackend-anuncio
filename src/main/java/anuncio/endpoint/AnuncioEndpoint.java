package anuncio.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import anuncio.services.AnuncioServiceImpl;
import anuncio.exceptions.ObjectNotFoundException;
import anuncio.exceptions.RegraNegocioException;
import anuncio.database.entity.AnuncioDatabase;
import anuncio.domain.dto.AnuncioDto;
import anuncio.services.mapper.AnuncioMapperImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/anuncios")
@CrossOrigin("*")
public class AnuncioEndpoint {

    @Autowired
    private AnuncioServiceImpl service;

    @Autowired
    private AnuncioMapperImpl map;

    private static final Logger logger = LoggerFactory.getLogger(AnuncioEndpoint.class);

    @PostMapping
    public ResponseEntity post(@Validated @RequestBody AnuncioDto in) {
        try {
            logger.info("Endpoint post Anuncio ");
            service.post(in);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity put(@Validated @RequestBody AnuncioDto in) {
        try {
            service.put(in);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listAll() {
        logger.info("Endpoint listall - Inicio ");
        try {
            List<AnuncioDto> out = service.listAll();
            return ResponseEntity.ok(out);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());
        }
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity listarPorUsuario(@PathVariable String id) {
        try {
            List<AnuncioDto> out = service.findByUser(id);
            return ResponseEntity.ok(out);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        try {
            AnuncioDatabase out = service.findById(id);
            AnuncioDto dto = map.databaseToDto(out);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no processamento: " + e.getMessage());

        }
    }
}