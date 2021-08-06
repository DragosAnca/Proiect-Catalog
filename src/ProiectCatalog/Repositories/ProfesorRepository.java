package ProiectCatalog.Repositories;

import ProiectCatalog.Profesor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProfesorRepository implements IProfesorRepository{
    private ArrayList<Profesor> profesori = new ArrayList<>();
    private Path path;

    public ProfesorRepository(Path path) {
        this.path = Paths.get("Profesor.CSV");
    }

    private void readProfesori() {

        try (BufferedReader br = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {


            String line = br.readLine();


            while (line != null) {


                String[] attributes = line.split(",");

                Profesor profesor = createProfesor(attributes);

                profesori.add(profesor);

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    private Profesor createProfesor(String[] attributes) {
        int idProfesor = Integer.parseInt(attributes[0]);
        String numeProfesor = attributes[1];

        return new Profesor(idProfesor, numeProfesor);

    }


    @Override
    public ArrayList<Profesor> getAll() {
        readProfesori();
        return profesori;
    }
}
