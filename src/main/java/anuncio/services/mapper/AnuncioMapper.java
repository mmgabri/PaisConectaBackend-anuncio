package anuncio.services.mapper;

import org.springframework.stereotype.Service;

@Service
public interface AnuncioMapper<T1, T2> {

    T2 dtoToDatabase(T1 obj);

    T1 databaseToDto(T2 obj);
}

