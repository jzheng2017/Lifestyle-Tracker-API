package webservice.services;

import webservice.dao.interfaces.CategoryDao;
import webservice.dto.Category;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao;

    @Inject
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAll() {
        return this.categoryDao.getAll();
    }


    public Category getCategory(int categoryId) {
        return this.categoryDao.getCategory(categoryId);
    }

    public List<Category> getChildren(int parentId) {
        return this.categoryDao.getChildren(parentId);
    }
}
