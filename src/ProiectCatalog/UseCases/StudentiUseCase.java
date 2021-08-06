package ProiectCatalog.UseCases;

import ProiectCatalog.Repositories.IStudentRepository;

import ProiectCatalog.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentiUseCase implements IUseCase {
    public String name = "Studenti";
    String description = "- operatii pe tabela de studenti";
    IStudentRepository studentRepository;


    public StudentiUseCase(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute() {
        boolean ok = true;
        Scanner scanner = new Scanner(System.in);
        while(ok) {
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
                    try {removeByName(scanner.nextLine());}
                    catch (IOException ioe){
                        System.out.println(ioe);}
                    break;
                case "3":
                    System.out.println("Add a student by writing their info in this order:\n name\n surname\n year of study");
                    addStudent(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
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

    void showAll(){
        ArrayList<Student> studenti = studentRepository.getAll();
        for (Student student :
                studenti) {
            System.out.println(student);

        }
    }

    public void addStudent(String name, String surname, String year) {
        Student student = new Student(0, name, surname, year);
        studentRepository.add(student);

    }

    public void removeByName(String name) throws IOException {
        ArrayList<Student> studenti = studentRepository.getAll();
        for (Student student :
                studenti) {
            if (student.nume.equals(name)) {
                studentRepository.remove(student.nume);
                break;
            }
        }
    }
}
