package ProiectCatalog.Repositories;

import ProiectCatalog.Materie;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MaterieRepository implements IMaterieRepository{

    private ArrayList<Materie> materii;
    private Path path;

    public MaterieRepository() {
        //this.materii = readMaterii();
        //this.path = Paths.get("src/Etapa1/Materie.CSV");
    }


    private ArrayList<Materie> readMaterii() {
        ArrayList<Materie> materii = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Path.of("src/Etapa1/Materie.CSV"),
                StandardCharsets.UTF_8)) {

            String line = br.readLine();
            line = br.readLine();


            while (line != null) {


                String[] attributes = line.split(",");

                Materie materie = createMaterie(attributes);

                materii.add(materie);

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return materii;

    }

    private Materie createMaterie(String[] attributes) {
        int idMaterie = Integer.parseInt(attributes[0]);
        String numeMaterie = attributes[1];
        String idProfesor = attributes[2];

        return new Materie(idMaterie, numeMaterie,idProfesor);

    }

    @Override
    public ArrayList<Materie> getAll() {
        return materii;
    }

    @Override
    public Materie getById(int id) {
        ArrayList<Materie> materii = readMaterii();
        Materie materieById;
        materieById = materii
                .stream()
                .filter(x -> x.idMaterie == id)
                .findFirst()
                .orElse(null);
        return materieById;
    }

    @Override
    public void remove(String numeMaterie) {
        deleteMaterie(numeMaterie);
    }

    private void deleteMaterie(String numeMaterie) {
        for(int i = 0; i <= materii.size(); i++) {
            if (materii.get(i).numeMaterie.equals(numeMaterie)) {
                materii.remove(materii.get(i));
                saveChanges();
                break;
            }
        }
    }

    public void saveChanges() {
        File temp = new File("src/Etapa1/Materie.CSV");
        temp.delete();
        File file = new File("src/Etapa1/Materie.CSV");
        try (FileWriter fw = new FileWriter(file)){
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("idMaterie,numeMaterie,idProfesor");
            for (Materie materie:
                    materii) {

                bw.newLine();
                bw.write(materie.idMaterie
                        +","
                        +materie.numeMaterie
                        +","
                        +materie.idProfesor);

            }
            bw.close();
        }catch (IOException ioe){
            System.out.println(ioe);}
    }

    private int createNewId(){
        int nextId = 0;
        for (Materie materie :
                materii) {
            if(materie.idMaterie > nextId)
                nextId = materie.idMaterie;//TODO convert idMaterie from string to int and resolce add methood
            nextId++;
        }
        return nextId;
    }

    @Override
    public void add(Materie materie) {
        materie.idMaterie = createNewId();
        materii.add(materie);
        saveChanges();


    }

}
