package ProiectCatalog;

import ProiectCatalog.Repositories.DBConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static final Connection connection = DBConnectionUtil.getInstance();

    public static void main(String[] args) {
        Bootstrapper bootstrapper = new Bootstrapper(connection);
        CatalogApplication catalogApplication = bootstrapper.buildApplication();
        catalogApplication.run();
    }
}
