package webservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import webservice.entities.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAllByParentId(int parentId, Sort by);

    List<Category> findAll(Sort by);
}
