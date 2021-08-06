package ProiectCatalog;

public class Nota {
    public int idNota;
    public int idMaterie;
    public int idStudent;
    public int nota;

    public Nota(int nota, int idMaterie, int idStudent, int idNota) {
        this.nota = nota;
        this.idMaterie = idMaterie;
        this.idStudent = idStudent;
        this.idNota = idNota;
    }
}
