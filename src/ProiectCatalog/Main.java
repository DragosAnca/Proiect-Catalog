package ProiectCatalog;

import ProiectCatalog.Repositories.DBConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DBConnectionUtil.getInstance();
            Bootstrapper bootstrapper = new Bootstrapper();
            CatalogApplication catalogApplication = bootstrapper.buildApplication(connection);
            catalogApplication.run();
        } catch (SQLException e) {
            e.printStackTrace();
            DBConnectionUtil.closeDBConnection(connection);
        }
    }
}
