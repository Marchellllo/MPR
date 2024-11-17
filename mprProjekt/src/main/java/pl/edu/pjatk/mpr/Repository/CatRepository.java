package pl.edu.pjatk.mpr.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.mpr.Model.Cat;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    public List<Cat> findByName(String name);
    public List<Cat> findByColor(String color);
    Optional<Cat> findByIdentificator(Long identificator);


}
