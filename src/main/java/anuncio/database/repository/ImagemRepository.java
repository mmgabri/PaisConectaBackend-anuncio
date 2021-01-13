package anuncio.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import anuncio.database.entity.ImagemDatabase;
import org.springframework.transaction.annotation.Transactional;

public interface ImagemRepository extends JpaRepository<ImagemDatabase, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ImagemDatabase WHERE anuncio_id = :anuncioId")
    void deleteByAnuncio (@Param("anuncioId") Integer anuncio_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ImagemDatabase WHERE usuario_id = :usuarioId")
    void deleteByUsuario (@Param("usuarioId") Integer usuario_id);

}

