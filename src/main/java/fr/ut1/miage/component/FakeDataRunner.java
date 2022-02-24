package fr.ut1.miage.component;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import fr.ut1.miage.model.*;
import fr.ut1.miage.service.impl.*;
import fr.ut1.miage.util.Constant;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FakeDataRunner implements CommandLineRunner {

    private final static Random random = new Random();
    private final static Faker faker = new Faker(new Locale("fr"));
    private final static SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    private final CentreDistributeurServiceImpl centreDistributeurService;
    private final InstitutFormationServiceImpl institutFormationService;
    private final PaysServiceImpl paysService;
    private final VilleServiceImpl villeService;
    private final ClientServiceImpl clientService;
    private final TypeJourServiceImpl typeJourService;
    private final DiplomeServiceImpl diplomeService;
    private final JournalisteServiceImpl journalisteService;

    @Autowired
    public FakeDataRunner(CentreDistributeurServiceImpl centreDistributeurService,
                          InstitutFormationServiceImpl institutFormationService,
                          PaysServiceImpl paysService,
                          VilleServiceImpl villeService,
                          ClientServiceImpl clientService,
                          TypeJourServiceImpl typeJourService,
                          DiplomeServiceImpl diplomeService,
                          JournalisteServiceImpl journalisteService) {
        this.centreDistributeurService = centreDistributeurService;
        this.institutFormationService = institutFormationService;
        this.paysService = paysService;
        this.villeService = villeService;
        this.clientService = clientService;
        this.typeJourService = typeJourService;
        this.diplomeService = diplomeService;
        this.journalisteService = journalisteService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Map<String, ArrayList<String>> worldCities = getWorldCities();
            loadPaysVilleCentreDistributeur(worldCities);
            loadInstitutFormation();
            loadDiplome();
            loadClient();
            loadTypeJour();
            loadJournaliste();
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
                    nom = faker.name().lastName();
                    prenom = faker.name().firstName();
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
        Constant.CONSTRAINT_NOMTYJ.forEach(s -> typeJourService.create(new TypeJour(s, Precision.round(random.nextFloat(1, 10), 2))));
    }

    private void loadDiplome() {
        List<InstitutFormation> institutFormations = institutFormationService.getAll();
        Constant.CONSTRAINT_NOMDY.forEach(s -> diplomeService.create(new Diplome(s, randomInList(institutFormations))));
    }

    private void loadJournaliste() {
        List<Diplome> diplomes = diplomeService.getAll();
        List<TypeJour> typeJours = typeJourService.getAll();
        try {
            for (int i = 0; i < 100; i++) {
                journalisteService.create(Journaliste.builder()
                        .nom(faker.name().firstName())
                        .prenom(faker.name().firstName())
                        .diplome(randomInList(diplomes))
                        .typeJour(randomInList(typeJours))
                        .dateObtention(faker.date().between(SDF.parse("01-01-2000"), SDF.parse("01-01-2015")))
                        .build());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private <T> T randomInList(List<T> objects) {
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
