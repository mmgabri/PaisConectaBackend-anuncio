package anuncio.services.mapper;

import anuncio.database.entity.ImagemDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import anuncio.adapter.UsuarioClient;
import anuncio.database.entity.AnuncioDatabase;
import anuncio.domain.dto.AnuncioDto;
import anuncio.utils.Utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioMapperImpl implements AnuncioMapper<AnuncioDto, AnuncioDatabase> {

    @Autowired
    Utils utils;

    @Override
    public AnuncioDatabase dtoToDatabase(AnuncioDto dto) {

        AnuncioDatabase anuncioDatabase = AnuncioDatabase.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .tipo(dto.getTipo())
                .categoria(dto.getCategoria())
                .valor(dto.getValor())
                .cep((dto.getCep()))
                .idUsuario(dto.getIdUsuario())
                .status(dto.getStatus())
                .dataHoraCriacao(LocalDateTime.now())
                .build();

        return anuncioDatabase;
    }

    @Override
    public AnuncioDto databaseToDto(AnuncioDatabase anuncioDatabase) {

        List<String> listImg = new ArrayList<>();
        for (ImagemDatabase objList : anuncioDatabase.getImagens()) {
            listImg.add(objList.getUri());
        }

        AnuncioDto anuncioDto = AnuncioDto.builder()
                .id(anuncioDatabase.getId())
                .titulo(anuncioDatabase.getTitulo())
                .descricao(anuncioDatabase.getDescricao())
                .tipo(anuncioDatabase.getTipo())
                .categoria(anuncioDatabase.getCategoria())
                .valor(anuncioDatabase.getValor())
                .cep(anuncioDatabase.getCep())
                .idUsuario(anuncioDatabase.getIdUsuario())
                .imagens(listImg)
                .status(anuncioDatabase.getStatus())
                .dataHoraAnuncio(utils.formatterDateTime(anuncioDatabase.getDataHoraCriacao(), "dd/MM/yyyy"))
                .build();

        return anuncioDto;
    }

}
