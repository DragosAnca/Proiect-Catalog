package Business.UseCases;

import ProiectCatalog.CatalogApplication;

public class ReturnUseCase implements IUseCase{

    private String name = "0.Return";
    private String description = "Returns to the main UseCases";



    @Override
    public void execute() {
        //TODO Resolve ReturnUseCase After MateriiView and NoteView
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
