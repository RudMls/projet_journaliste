package fr.ut1.miage.component;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import fr.ut1.miage.model.*;
import fr.ut1.miage.service.impl.*;
import fr.ut1.miage.util.Constant;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FakeDataRunner implements CommandLineRunner {

    private InstitutFormationServiceImpl institutFormationService;
    private CentreDistributeurServiceImpl centreDistributeurService;
    private PaysServiceImpl paysService;
    private VilleServiceImpl villeService;
    private ClientServiceImpl clientService;
    private TypeJourServiceImpl typeJourService;

    @Override
    public void run(String... args) throws Exception {
        try {
            Map<String, ArrayList<String>> worldCities = getWorldCities();
            loadPaysVilleCentreDistributeur(worldCities);
            loadInstitutFormation();
            loadClient();
            loadTypeJour();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void loadPaysVilleCentreDistributeur(Map<String, ArrayList<String>> worldCities) {
        worldCities.forEach((key, value) -> {
            Pays pays = new Pays(key);
            paysService.create(pays);
            value.forEach(s -> {
                CentreDistributeur centreDistributeur = new CentreDistributeur(s, pays);
                centreDistributeurService.create(centreDistributeur);
                villeService.create(new Ville(s, centreDistributeur));
            });
        });
    }

    private void loadClient() {
        List<Ville> villes = villeService.getAll();
        String nom, prenom;
        Ville ville;
        if (!villes.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                do {
                    nom = faker().name().lastName();
                    prenom = faker().name().firstName();
                } while (clientService.existsByNomAndPrenom(nom, prenom));
                ville = randomInList(villes);
                clientService.create(new Client(nom, prenom, ville));
            }
        }

    }

    private void loadInstitutFormation() {
        Constant.CONSTRAINT_NOMIF.forEach(s -> institutFormationService.create(new InstitutFormation(s)));
    }

    private void loadTypeJour() {
        Constant.CONSTRAINT_NOMTYJ.forEach(s -> typeJourService.create(new TypeJour(s, Precision.round(random().nextFloat(1, 10), 2))));
    }

    private Faker faker() { return new Faker(new Locale("fr")); }

    private Random random() {
        return new Random();
    }

    private <T> T randomInList(List<T> objects) {
        Random random = new Random();
        return objects.get(random.nextInt(objects.size()));
    }

    private Map<String, ArrayList<String>> getWorldCities() {
        Map<String, ArrayList<String>> worldCities = new HashMap<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("data/worldcities.csv"))) {
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                if (!worldCities.containsKey(values[4])) {
                    worldCities.put(values[4], new ArrayList<>(List.of(values[0])));
                } else {
                    worldCities.get(values[4]).add(values[0]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return  worldCities.entrySet()
                .stream()
                .filter(entry -> List.of("France", "Spain").contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
