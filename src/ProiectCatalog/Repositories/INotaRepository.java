package ProiectCatalog.Repositories;

import ProiectCatalog.Nota;


import java.util.ArrayList;

public interface INotaRepository {

    void readNote();
    ArrayList<Nota> getAll();
}
