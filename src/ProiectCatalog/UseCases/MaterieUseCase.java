package ProiectCatalog.UseCases;

import ProiectCatalog.Materie;
import ProiectCatalog.Repositories.IMaterieRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaterieUseCase implements IUseCase{
    public String name = "Materii";
    String description = "- operatii pe tabela de materii";
    IMaterieRepository materieRepository;

    public MaterieUseCase(IMaterieRepository materieRepository) {
        this.materieRepository = materieRepository;
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
                    try {removeByName(scanner.nextLine());}
                    catch (IOException ioe){
                        System.out.println(ioe);}
                    break;
                case "3":
                    System.out.println("Add a class by writing their info in this order:\n name\n Professor id");
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
        ArrayList<Materie> materii = materieRepository.getAll();
        for (Materie materie :
                materii) {
            System.out.println(materie);
        }
    }

    public void addMaterie(String name, String idProf) {
        Materie materie = new Materie(0,name,idProf);
        materieRepository.add(materie);
    }

    public void removeByName(String name) throws IOException {
        ArrayList<Materie> materii = materieRepository.getAll();
        for (Materie materie :
                materii) {
            if(materie.numeMaterie.equals(name)){
                materieRepository.remove(materie.numeMaterie);
                break;
            }
        }
    }




}
