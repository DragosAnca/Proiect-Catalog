package View.ViewsStudenti;

import Business.UseCases.IUseCase;
import Business.Views.IViewsStudenti.IViewStudenti;

import java.util.List;
import java.util.Scanner;

public class ViewStudenti implements IViewStudenti {

    private List<IUseCase> useCases;
    public ViewStudenti(List<IUseCase> useCases) {
        this.useCases = useCases;
    }

    @Override
    public void displayUseCases() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printUseCases();
            System.out.println("Please select use case");
            String input = scanner.nextLine();
            IUseCase useCase = chooseUseCase(input);
            if (useCase != null)
                useCase.execute();
        }
    }

    void printUseCases() {
        for (IUseCase useCase :
                useCases) {
            System.out.println(useCase.getName());

        }
    }

    private IUseCase chooseUseCase(String input) {
        for (IUseCase useCase :
                useCases) {
            if (useCase.getName().contains(input)) {
                return useCase;
            }
        }
        return null;
    }
}
