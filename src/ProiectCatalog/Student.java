package ProiectCatalog;


public class Student {
    public Integer idStudent;
    public String nume;
    public String prenume;
    public int anDeStudiu;

    public Student(Integer idStudent, String nume, String prenume, int anDeStudiu) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.prenume = prenume;
        this.anDeStudiu = anDeStudiu;
    }

    @Override
    public String toString() {
        return "\nNume: " + nume + "\nPrenume: " + prenume + "\nAn de studiu: " + anDeStudiu;
    }
}
