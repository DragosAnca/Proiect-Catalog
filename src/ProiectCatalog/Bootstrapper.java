package ProiectCatalog;

//import ProiectCatalog.Repositories.MaterieRepository;
import ProiectCatalog.Repositories.StudentRepository;
import ProiectCatalog.UseCases.IUseCase;
import ProiectCatalog.UseCases.MaterieUseCase;
import ProiectCatalog.UseCases.StudentiUseCase;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class Bootstrapper {
    public CatalogApplication buildApplication(Connection connection) {
        StudentRepository studentRepository = new StudentRepository(connection);
//        MaterieRepository materieRepository = new MaterieRepository(connection);

        List<IUseCase> useCases = Arrays.asList(new StudentiUseCase(studentRepository) /*new MaterieUseCase((materieRepository)*/ );
        return new CatalogApplication(useCases);
    }


}
