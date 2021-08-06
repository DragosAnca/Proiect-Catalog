package ProiectCatalog;

public class Profesor {

    int idProfesor;
    String numeProfesor;

    public Profesor(int idProfesor, String numeProfesor) {
        this.idProfesor = idProfesor;
        this.numeProfesor = numeProfesor;
    }

    @Override
    public String toString() {
        return "\nNume Profesor"
                +numeProfesor;
    }
}
