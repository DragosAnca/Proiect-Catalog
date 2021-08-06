package ProiectCatalog.Repositories;

import ProiectCatalog.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IStudentRepository {

    List<Student> getAll() throws SQLException;

    Student getById(int id) throws SQLException;

    void remove(String name) throws IOException;

    void add(String[] attributes) throws IOException;
}
