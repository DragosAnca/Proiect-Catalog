package ProiectCatalog;

//import Database.Repositories.MaterieRepository;
import Business.UseCases.MaterieUseCase;
import Business.UseCases.NoteUseCase;
import Database.DBConnectionUtil;
import Database.MaterieRepository;
import Database.NotaRepository;
import Database.StudentRepository;
import Business.UseCases.IUseCase;
import Business.UseCases.StudentiUseCase;


import java.util.Arrays;
import java.util.List;

public class Bootstrapper {
    public CatalogApplication buildApplication() {
        DBConnectionUtil dbConnectionUtil = new DBConnectionUtil();
        StudentRepository studentRepository = new StudentRepository(dbConnectionUtil);
        MaterieRepository materieRepository = new MaterieRepository(dbConnectionUtil);
        NotaRepository notaRepository = new NotaRepository(dbConnectionUtil);

        List<IUseCase> useCases = Arrays.asList(new StudentiUseCase(studentRepository) ,new MaterieUseCase((materieRepository)), new NoteUseCase(notaRepository));
        return new CatalogApplication(useCases);
    }


}
