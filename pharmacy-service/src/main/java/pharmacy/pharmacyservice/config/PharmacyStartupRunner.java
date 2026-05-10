package pharmacy.pharmacyservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class PharmacyStartupRunner implements CommandLineRunner {


    @Value("${app.branch-name}")
    private String branchName;

    @Value("${app.hotline}")
    private String hotline;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Branch name: " + branchName);
        System.out.println("Hotline: " + hotline);
        System.out.println("Database URL: " + datasourceUrl);


    }
}
