package Business.UseCases;

import Business.Models.Nota;
import Business.Repositories.INotaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoteUseCase implements IUseCase {
    private final String name = "Note";
    private final String description = "- operatii pe tabela de note";
    INotaRepository notaRepository;

    public NoteUseCase(INotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public INotaRepository notaRepository() {
        return notaRepository;
    }

    public void execute() {
        boolean ok = true;
        Scanner scanner = new Scanner(System.in);
        while (ok) {
            System.out.println("Please select an option: ");
            System.out.println("1. See all grades");
            System.out.println("2. Remove a grade");
            System.out.println("3. Add grade");
            System.out.println("0. Cancel");
            switch (scanner.nextLine()) {
                case "1":
                    showAll();
                    break;
                case "2":
                    System.out.println("What grade to remove by ID: ");
                    removeById(Integer.parseInt(scanner.nextLine()));
                    break;
                case "3":
                    System.out.println("Add a grade by writing it's info in this order:\n Student's name\n Name of the class\n Grade");
                    addNota(scanner.nextLine(), scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
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
            List<Nota> note = notaRepository.getAll();
            for (Nota nota : note) {
                System.out.println(nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNota(String numeStudent, String numeProfesor, int grade) {
        Nota nota = new Nota(0, numeStudent, numeProfesor, grade);
        try {
            notaRepository.add(nota);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeById(int id) {
        List<Nota> note = null;
        try {
            note = notaRepository.getAll();
            for (Nota nota : note) {
                if (nota.getIdNota() == id) {
                    notaRepository.remove(nota.getIdNota());
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
