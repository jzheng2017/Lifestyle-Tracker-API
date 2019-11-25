package webservice.dao;

import webservice.dao.interfaces.CategoryDao;
import webservice.datasource.core.Database;
import webservice.dto.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private Database db = new Database("category");

    @Override
    public Category getCategory(int categoryId) {
        return createCategory(db.query("select.category.by.id", new String[]{Integer.toString(categoryId)}).execute());
    }

    @Override
    public boolean updateCategory(Category user) {
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return false;
    }

    @Override
    public boolean insertCategory(Category user) {
        return false;
    }

    @Override
    public List<Category> getAll() {
        return createCategories(db.query("select.all.categories", null).execute());
    }

    @Override
    public List<Category> getChildren(int parentId) {
        return createCategories(db.query("select.all.children.by.parent.id", new String[] {Integer.toString(parentId)}).execute());
    }

    private List<Category> createCategories(ResultSet result) {
        List<Category> categories = new ArrayList<>();
        try {
            while (result.next()) {
                categories.add(createCategory(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private Category createCategory(ResultSet result) {
        try {
            if (result.getRow() < 1 && result.next() && result.getRow() > 1) result.previous();
            if (result.getRow() > 0) {
                int id = result.getInt("id");
                String name = result.getString("category_name");
                int parentId = result.getInt("parent_id");
                String createdAt = result.getString("created_at");
                String updatedAt = result.getString("updated_at");
                return new Category(id, name, parentId, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
