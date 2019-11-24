package webservice.dao;

import webservice.dao.interfaces.UserDao;
import webservice.datasource.core.Database;
import webservice.dto.User;
import webservice.exceptions.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Database db = new Database("user");

    @Override
    public User getUser(int userId) {
        User user = createUser(db.query("select.user.by.id", new String[]{Integer.toString(userId)}).execute());
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public boolean updateUser(User user) {
        String[] parameters = {
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                Integer.toString(user.getId())
        };
        return db.query("update.user.by.id", parameters).executeUpdate() > 0;

    }

    @Override
    public boolean deleteUser(int userId) {
        return db.query("delete.user.by.id", new String[]{Integer.toString(userId)}).executeUpdate() > 0;
    }

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public List<User> getAll() {
        ResultSet resultSet = db.query("select.all.users", null).execute();
        return createUsers(resultSet);
    }

    private List<User> createUsers(ResultSet result) {
        List<User> users = new ArrayList<>();
        try {
            while (result.next()) {
                users.add(createUser(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User createUser(ResultSet result) {
        try {
            if (result.getRow() < 1 && result.next() && result.getRow() > 1) result.previous();
            if (result.getRow() > 0) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String username = result.getString("username");
                String createdAt = result.getString("created_at");
                String updatedAt = result.getString("updated_at");
                return new User(id, firstName, lastName, username, email, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
