package Business.UseCases;

import Business.Models.Materie;
import Business.Repositories.IMaterieRepository;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

public class MaterieUseCase implements IUseCase{
    private final String name = "Materii";
    private final String description = "- operatii pe tabela de materii";
    IMaterieRepository materieRepository;

    public MaterieUseCase(IMaterieRepository materieRepository) {
        this.materieRepository = materieRepository;
    }

    public IMaterieRepository getMaterieRepository() {
        return materieRepository;
    }

    @Override
    public void execute() {
        boolean ok = true;
        Scanner scanner = new Scanner(System.in);
        while(ok) {
            System.out.println("Please select an option: ");
            System.out.println("1. See all ");
            System.out.println("2. Remove class");
            System.out.println("3. Add class");
            System.out.println("0. Cancel");
            switch (scanner.nextLine()) {
                case "1":
                    showAll();
                    break;
                case "2":
                    System.out.println("What class to remove: ");
                    removeByName(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Add a class by writing their info in this order:\n Nume Materie\n Nume Profesor");
                    addMaterie(scanner.nextLine(), scanner.nextLine());
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
            List<Materie> materii = materieRepository.getAll();
            for (Materie materie : materii) {
                System.out.println(materie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMaterie(String name, String idProf) {
        Materie materie = new Materie(0, name, idProf);
        try {
            materieRepository.add(materie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeByName(String name) {
        List<Materie> materii = null;
        try {
            materii = materieRepository.getAll();
            for (Materie materie : materii) {
                if (materie.getNumeMaterie().equals(name)) {
                    materieRepository.remove(materie.getNumeMaterie());
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
