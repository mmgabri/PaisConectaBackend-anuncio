package anuncio.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  AnuncioService <T1, T2> {
    void post (T1 obj);
    void put (T1 obj);
    void delete (Integer id);
    T2 findById(Integer id);
    List<T1> listByFilter (T2 obj);
    List<T1> listAll ();
}
