package anuncio.services;

import anuncio.domain.dto.UsuarioDto;
import anuncio.domain.enums.StatusAnuncioEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import anuncio.adapter.UsuarioClient;
import anuncio.database.entity.AnuncioDatabase;
import anuncio.database.repository.AnuncioRepository;
import anuncio.domain.dto.AnuncioDto;
import anuncio.exceptions.RegraNegocioException;
import anuncio.services.mapper.AnuncioMapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnuncioServiceImpl implements AnuncioService<AnuncioDto, AnuncioDatabase> {

    @Autowired
    AnuncioRepository repo;

    @Autowired
    AnuncioMapperImpl map;

    @Autowired
    ImagemService imagemService;

    @Autowired
    UsuarioClient usuarioClient;

    private static final Logger logger = LoggerFactory.getLogger(AnuncioServiceImpl.class);

    @Override
    @Transactional
    public void post(AnuncioDto anuncioDto) {

        anuncioDto.setId(null);
        anuncioDto.setStatus(StatusAnuncioEnum.ATIVO);

        UsuarioDto usuarioDto = new UsuarioDto();
        try {
            logger.info("Vai chamar api usuários ");
            usuarioDto = usuarioClient.get(anuncioDto.getIdUsuario());
            logger.info("Chamda da api usuários com sucesso ");
        } catch (Exception e) {
            logger.info("Erro na chamada da api usuários: "+e);
            throw new RegraNegocioException("Erro na chamada da api usuários");
        }

        if (usuarioDto == null) {
            logger.error("Usuário inválido.");
            throw new RegraNegocioException("Usuário inválido");
        }

        AnuncioDatabase dbBefore = map.dtoToDatabase(anuncioDto);

        AnuncioDatabase dbAfter = repo.save(dbBefore);

        imagemService.post(dbAfter, anuncioDto);

        return;
    }

    @Override
    @Transactional
    public void put(AnuncioDto anuncioDto) {

        AnuncioDatabase anuncioDatabase = new AnuncioDatabase();

        if (anuncioDto.getId() == null) {
            throw new RegraNegocioException("Código do anúncio não informado");
        } else {
            anuncioDatabase = findById(anuncioDto.getId());
        }

        anuncioDto.setStatus(anuncioDatabase.getStatus());

        AnuncioDatabase dbBefore = map.dtoToDatabase(anuncioDto);

        AnuncioDatabase dbAfter = repo.save(dbBefore);

        imagemService.put(dbAfter, anuncioDto);

        return;
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        AnuncioDatabase dbAnuncio = findById(id);
        repo.delete(dbAnuncio);
    }

    @Override
    @Transactional(readOnly = true)
    public AnuncioDatabase findById(Integer id) {

        Optional<AnuncioDatabase> dbAnuncio = repo.findById(id);

        if (dbAnuncio.isEmpty()) {
            throw new RegraNegocioException("Anuncio não encontrado");
        }

        return dbAnuncio.get();
    }

    @Transactional(readOnly = true)
    public List<AnuncioDto> findByUser(String id) {

        List<AnuncioDatabase> anuncioDatabaseList = repo.findByIdUsuario(id);

        List<AnuncioDto> anuncioDtoList = new ArrayList<>();

        for (AnuncioDatabase objList : anuncioDatabaseList) {
            AnuncioDto objDto = map.databaseToDto(objList);
            anuncioDtoList.add(objDto);
        }
        return anuncioDtoList;
    }


    @Override
    @Transactional(readOnly = true)
    public List<AnuncioDto> listByFilter(AnuncioDatabase uniformeDatabase) {

        Example<AnuncioDatabase> example = Example.of(uniformeDatabase,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<AnuncioDatabase> dbAnuncioList = repo.findAll(example);

        List<AnuncioDto> dtoUniformeList = new ArrayList<>();

        for (AnuncioDatabase objList : dbAnuncioList) {
            AnuncioDto objDto = map.databaseToDto(objList);
            dtoUniformeList.add(objDto);
        }

        return dtoUniformeList;
    }


    @Override
    public List<AnuncioDto> listAll() {
        List<AnuncioDatabase> anuncioDatabaseList = repo.findAll();

        List<AnuncioDto> anuncioDtoList = new ArrayList<>();

        for (AnuncioDatabase objList : anuncioDatabaseList) {
            AnuncioDto objDto = map.databaseToDto(objList);
            anuncioDtoList.add(objDto);
        }
        return anuncioDtoList;
    }

}