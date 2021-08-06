package ProiectCatalog;

public class Materie {
    public int idMaterie;
    public String numeMaterie;
    public String idProfesor;

    public Materie(int idMaterie, String numeMaterie,String idProfesor) {
        this.idMaterie = idMaterie;
        this.numeMaterie = numeMaterie;
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return "\nNume: "
                + numeMaterie
                +"\nidProfesor"
                + idProfesor;
    }
}
