package ProiectCatalog.Repositories;

import ProiectCatalog.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student getById(int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        /* Se executa query-ul*/
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Student(id, resultSet.getString("nume"), resultSet.getString("prenume"),
                    resultSet.getInt("anDeStudiu"));
        }
        return null;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String query = "SELECT * FROM student";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        List<Student> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Student(resultSet.getInt("idStudent"), resultSet.getString("nume"), resultSet.getString("prenume"),
                    resultSet.getInt("anDeStudiu")));
        }
        return list;
    }

    @Override
    public void add(Student student) throws SQLException {
        String query = "INSERT INTO student (nume, prenume, anDeStudiu) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, student.getNume());
        statement.setString(2, student.getPrenume());
        statement.setInt(3, student.getAnDeStudiu());

        statement.executeUpdate();
    }

    @Override
    public void remove(String name) throws SQLException {
        String query = "DELETE FROM student where nume = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.executeUpdate();
    }
}


