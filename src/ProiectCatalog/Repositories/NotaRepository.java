package ProiectCatalog.Repositories;

import ProiectCatalog.Nota;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NotaRepository implements INotaRepository{
    private ArrayList<Nota> note = new ArrayList<>();
    private Path path;

    public NotaRepository(Path path) {
        this.path = Paths.get("Nota.CSV");
    }

    @Override
    public void readNote() {


        try (BufferedReader br = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {


            String line = br.readLine();


            while (line != null) {


                String[] attributes = line.split(",");

                Nota nota = createNota(attributes);

                note.add(nota);

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    private Nota createNota(String[] attributes) {
        int idNota = Integer.parseInt(attributes[0]);
        int idMaterie = Integer.parseInt(attributes[1]);
        int idStudent = Integer.parseInt(attributes[2]);
        int nota = Integer.parseInt(attributes[3]);

        return new Nota(idNota ,idMaterie ,idStudent, nota);

    }

    @Override
    public ArrayList<Nota> getAll() {
        readNote();
        return note;
    }
}
