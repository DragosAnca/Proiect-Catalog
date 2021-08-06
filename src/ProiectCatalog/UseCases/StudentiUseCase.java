package ProiectCatalog.UseCases;

import ProiectCatalog.Repositories.IStudentRepository;

import ProiectCatalog.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentiUseCase implements IUseCase {
    private final String name = "Studenti";
    private final String description = "- operatii pe tabela de studenti";
    IStudentRepository studentRepository;

    public StudentiUseCase(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public IStudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void execute() {
        boolean ok = true;
        Scanner scanner = new Scanner(System.in);
        while (ok) {
            System.out.println("Please select an option: ");
            System.out.println("1. See all students");
            System.out.println("2. Remove student");
            System.out.println("3. Add student");
            System.out.println("0. Cancel");
            switch (scanner.nextLine()) {
                case "1":
                    showAll();
                    break;
                case "2":
                    System.out.println("What student to remove: ");
                    removeByName(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Add a student by writing their info in this order:\n name\n surname\n year of study");
                    addStudent(scanner.nextLine(), scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
                    break;
                case "0":
                    ok = false;
                    break;
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    void showAll() {
        try {
            List<Student> studenti = studentRepository.getAll();
            for (Student student : studenti) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String name, String surname, int year) {
        Student student = new Student(0, name, surname, year);
        try {
            studentRepository.add(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeByName(String name) {
        List<Student> studenti = null;
        try {
            studenti = studentRepository.getAll();
            for (Student student : studenti) {
                if (student.getNume().equals(name)) {
                    studentRepository.remove(student.getNume());
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
