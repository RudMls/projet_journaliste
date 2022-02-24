package fr.ut1.miage.component;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import fr.ut1.miage.model.*;
import fr.ut1.miage.model.embeddable.AbonnerId;
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

    private final Random random = new Random();
    private final Faker faker = new Faker(new Locale("fr"));
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private final CentreDistributeurServiceImpl centreDistributeurService;
    private final InstitutFormationServiceImpl institutFormationService;
    private final PaysServiceImpl paysService;
    private final VilleServiceImpl villeService;
    private final ClientServiceImpl clientService;
    private final TypeJourServiceImpl typeJourService;
    private final DiplomeServiceImpl diplomeService;
    private final JournalisteServiceImpl journalisteService;
    private final TypePUServiceImpl typePUService;
    private final PublicationServiceImpl publicationService;
    private final AbonnerServiceImpl abonnerService;

    @Autowired
    public FakeDataRunner(CentreDistributeurServiceImpl centreDistributeurService,
                          InstitutFormationServiceImpl institutFormationService,
                          PaysServiceImpl paysService,
                          VilleServiceImpl villeService,
                          ClientServiceImpl clientService,
                          TypeJourServiceImpl typeJourService,
                          DiplomeServiceImpl diplomeService,
                          JournalisteServiceImpl journalisteService,
                          TypePUServiceImpl typePUService,
                          PublicationServiceImpl publicationService,
                          AbonnerServiceImpl abonnerService) {
        this.centreDistributeurService = centreDistributeurService;
        this.institutFormationService = institutFormationService;
        this.paysService = paysService;
        this.villeService = villeService;
        this.clientService = clientService;
        this.typeJourService = typeJourService;
        this.diplomeService = diplomeService;
        this.journalisteService = journalisteService;
        this.typePUService = typePUService;
        this.publicationService = publicationService;
        this.abonnerService = abonnerService;
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
            loadTypePU();
            loadPublication();
            loadAbonner();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void loadPaysVilleCentreDistributeur(Map<String, ArrayList<String>> worldCities) {
        worldCities.forEach((key, value) -> {
            Pays pays = Pays.builder().nom(key).build();
            paysService.create(pays);
            value.forEach(s -> {
                CentreDistributeur centreDistributeur = CentreDistributeur.builder().ville(s).pays(pays).build();
                centreDistributeurService.create(centreDistributeur);
                villeService.create(Ville.builder().nom(s).centreDistributeur(centreDistributeur).build());
            });
        });
    }

    private void loadClient() {
        List<Ville> villes = villeService.getAll();
        String nom;
        String prenom;
        Ville ville;
        if (!villes.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                do {
                    nom = faker.name().lastName();
                    prenom = faker.name().firstName();
                } while (clientService.existsByNomAndPrenom(nom, prenom));
                ville = randomInList(villes);
                clientService.create(
                    Client.builder()
                        .nom(nom)
                        .prenom(prenom)
                        .ville(ville)
                    .build()
                );
            }
        }
    }

    private void loadInstitutFormation() {
        Constant.CONSTRAINT_NOMIF.forEach(s -> institutFormationService.create(InstitutFormation.builder().nom(s).build()));
    }

    private void loadTypeJour() {
        Constant.CONSTRAINT_NOMTYJ.forEach(s -> typeJourService.create(TypeJour.builder().nom(s).prixCaractere(Precision.round(random.nextFloat(1, 10), 2)).build()));
    }

    private void loadDiplome() {
        List<InstitutFormation> institutFormations = institutFormationService.getAll();
        Constant.CONSTRAINT_NOMDY.forEach(s -> diplomeService.create(Diplome.builder().nom(s).institutFormation(randomInList(institutFormations)).build()));
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
                        .dateObtention(faker.date().between(sdf.parse("01-01-2000"), sdf.parse("01-01-2015")))
                        .build());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void loadTypePU() {
        Constant.CONSTRAINT_NOMTY.forEach(s -> typePUService.create(TypePU.builder().nom(s).build()));
    }

    private void loadPublication() {
        List<Journaliste> journalistes = journalisteService.getAll();
        List<TypePU> typePUS = typePUService.getAll();
        for (int i = 0; i < 500; i++) {
            publicationService.create(
                    Publication.builder()
                            .periodicite(randomInList(Constant.CONSTRAINT_PERIODICITE))
                            .journaliste(randomInList(journalistes))
                            .typePU(randomInList(typePUS))
                            .build()
            );
        }
    }

    private void loadAbonner() {
        List<Client> clients = clientService.getAll().stream()
                .filter(client -> client.getCode() % 7 != 0)
                .toList();
        List<Publication> publications = publicationService.getAll();
        clients.forEach(client -> {
            Collections.shuffle(publications);
            publications.stream()
                .limit(random.nextInt((int) Precision.round(publications.size() * 0.05, 0)))
                .forEach(publication -> {
                    try {
                        AbonnerId abonnerId = AbonnerId.builder()
                                .client(client)
                                .publication(publication)
                                .dateDebut(faker.date().between(sdf.parse("01-01-2015"), new Date()))
                                .build();
                        abonnerService.create(Abonner.builder().id(abonnerId).build());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
        });
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
