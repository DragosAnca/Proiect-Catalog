package ProiectCatalog.Repositories;

import ProiectCatalog.Materie;

import java.util.ArrayList;


public interface IMaterieRepository {

    ArrayList<Materie> getAll();

    Materie getById(int id);

    void remove(String name);

    void add(Materie materie);

}
