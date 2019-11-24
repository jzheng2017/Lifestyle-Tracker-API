package webservice.dao.interfaces;

import webservice.dto.Category;

import java.util.List;

public interface CategoryDao {


    Category getCategory(int categoryId);

    boolean updateCategory(Category user);

    boolean deleteCategory(int categoryId);

    boolean insertCategory(Category user);

    List<Category> getAll();

    List<Category> getChildren(int parentId);
}
