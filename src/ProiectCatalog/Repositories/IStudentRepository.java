package ProiectCatalog.Repositories;

import ProiectCatalog.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentRepository {

    List<Student> getAll() throws SQLException;

    Student getById(int id) throws SQLException;

    void remove(String name) throws SQLException;

    void add(Student student) throws SQLException;
}
