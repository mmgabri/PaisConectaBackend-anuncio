package anuncio.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import anuncio.database.entity.AnuncioDatabase;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<AnuncioDatabase, Integer> {

    List<AnuncioDatabase> findByIdUsuario(String idUsuario);
}
