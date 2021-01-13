package anuncio.services;

import anuncio.database.entity.AnuncioDatabase;
import anuncio.database.entity.ImagemDatabase;
import anuncio.database.repository.ImagemRepository;
import anuncio.domain.dto.AnuncioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ImagemService {

    @Autowired
    ImagemRepository repo;

    public void post(AnuncioDatabase anuncioDatabase, AnuncioDto anuncioDto) {

        if (anuncioDto.getImagens()  == null){
            return;
        }

        if (anuncioDto.getImagens().size() == 0){
            return;
        }

        for (int i = 0; i < anuncioDto.getImagens().size(); i++) {
            ImagemDatabase img = ImagemDatabase.builder()
                    .id(null)
                    .uri(anuncioDto.getImagens().get(i))
                    .anuncio(anuncioDatabase)
                    .dataHoraCriacao(LocalDateTime.now())
                    .build();
            repo.save(img);
        }
    }

    public void put(AnuncioDatabase anuncioDatabase, AnuncioDto anuncioDto) {

        repo.deleteByAnuncio(anuncioDatabase.getId());

        post(anuncioDatabase, anuncioDto);

        return;
    }
}


