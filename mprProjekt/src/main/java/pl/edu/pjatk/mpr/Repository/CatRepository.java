package pl.edu.pjatk.mpr.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.mpr.Model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    public List<Cat> findByName(String name);
    public List<Cat> findByColor(String color);

}
