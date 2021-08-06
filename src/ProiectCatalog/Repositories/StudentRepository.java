package ProiectCatalog.Repositories;

import ProiectCatalog.Student;

import java.io.*;
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
    private void add(String[] attributes) throws SQLException {
        String query = "INSERT INTO student VALUES(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, -1);
        statement.setString(2, attributes[1]);
        statement.setString(3, attributes[2]);
        statement.setString(4, attributes[3]);

        statement.executeUpdate();
    }

    @Override
    public void remove(String name) {
        deleteStudent(name);
    }

    private void deleteStudent(String name) {
        for (int i = 0; i <= studenti.size(); i++) {
            if (studenti.get(i).nume.equals(name)) {
                studenti.remove(studenti.get(i));
                saveChanges();
                break;
            }
        }

    }

    public void saveChanges() {
        File temp = new File("src/Etapa1/Student.CSV");
        temp.delete();
        File file = new File("src/Etapa1/Student.CSV");
        try (FileWriter fw = new FileWriter(file)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("idStudent,nume,prenume,anDeStudiu");
            for (Student student :
                    studenti) {

                bw.newLine();
                bw.write(student.idStudent.toString()
                        + ","
                        + student.nume
                        + ","
                        + student.prenume
                        + ","
                        + student.anDeStudiu);

            }
            bw.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }


    @Override
    public void add(Student student) {
        student.idStudent = createNewId();
        studenti.add(student);
        saveChanges();
    }
}


