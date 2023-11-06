package at.jku.se.prse.team2.logbook.business;

import at.jku.se.prse.team2.logbook.entities.Category;
import at.jku.se.prse.team2.logbook.entities.Vehicle;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFacade {
    private Connection conn;

    public CategoryFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        conn = databaseConnection.getConnection();
    }

    public Category getCategoryById(Integer id) throws SQLException {
        Category category = null;
        String query = "SELECT * FROM category WHERE category_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = new Category();
                category.setCategory_id(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategory_id(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public void persistCategory(Category c) {

        String query = "INSERT INTO category (category_name) VALUES (?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, c.getName());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCatgoryById(Integer id) {
        String query = "DELETE FROM category WHERE category_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
