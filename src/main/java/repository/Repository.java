package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> list();

   Optional <T> findById(Integer id);

    void save(T t);

    void update(T t);

    void delete(T t);

}
