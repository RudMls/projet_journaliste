package fr.ut1.miage.component;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import fr.ut1.miage.model.*;
import fr.ut1.miage.model.embeddable.AbonnerId;
import fr.ut1.miage.model.embeddable.DiffuserPUId;
import fr.ut1.miage.model.embeddable.LivrerId;
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
    private final NumeroServiceImpl numeroService;
    private final AbonnerServiceImpl abonnerService;
    private final RubriqueServiceImpl rubriqueService;
    private final DiffuserPUServiceImpl diffuserPUService;
    private final LivrerServiceImpl livrerService;

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
                          NumeroServiceImpl numeroService,
                          AbonnerServiceImpl abonnerService,
                          RubriqueServiceImpl rubriqueService,
                          DiffuserPUServiceImpl diffuserPUService,
                          LivrerServiceImpl livrerService) {
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
        this.numeroService = numeroService;
        this.abonnerService = abonnerService;
        this.rubriqueService = rubriqueService;
        this.diffuserPUService = diffuserPUService;
        this.livrerService = livrerService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Pays & Ville & CentreDistributeur
            Map<String, ArrayList<String>> worldCities = getWorldCities();
            loadPaysVilleCentreDistributeur(worldCities);
            List<Ville> villes = villeService.getAll();
            // InstitutFormation
            loadInstitutFormation();
            List<InstitutFormation> institutFormations = institutFormationService.getAll();
            // TypeJour
            loadTypeJour();
            List<TypeJour> typeJours = typeJourService.getAll();
            // TypePU
            loadTypePU();
            List<TypePU> typePUS = typePUService.getAll();
            // Diplome
            loadDiplome(institutFormations);
            List<Diplome> diplomes = diplomeService.getAll();
            // Client
            loadClient(villes);
            List<Client> clients = clientService.getAll();
            // Journaliste
            loadJournaliste(diplomes, typeJours);
            List<Journaliste> journalistes = journalisteService.getAll();
            // Publication
            loadPublication(journalistes, typePUS);
            List<Publication> publications = publicationService.getAll();
            // Numero
            loadNumero(publications);
            List<Numero> numeros = numeroService.getAll();
            // Rubrique
            loadRubrique(journalistes, publications);
            List<Rubrique> rubriques = rubriqueService.getAll();
            // Livrer
            loadLivrer(numeros, clients);
            // Abonner
            loadAbonner(clients, publications);
            // DiffuserPU
            loadDiffuserPU(publications);
            // EcrireArticle
            loadEcrireArticle(journalistes, numeros, rubriques);
            // CoutArticle
            loadCoutArticle(rubriques, journalistes);

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

    private void loadClient(List<Ville> villes) {
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

    private void loadTypePU() {
        Constant.CONSTRAINT_NOMTY.forEach(s -> typePUService.create(TypePU.builder().nom(s).build()));
    }

    private void loadDiplome(List<InstitutFormation> institutFormations) {
        Constant.CONSTRAINT_NOMDY.forEach(s -> diplomeService.create(Diplome.builder().nom(s).institutFormation(randomInList(institutFormations)).build()));
    }

    private void loadJournaliste(List<Diplome> diplomes, List<TypeJour> typeJours) {
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

    private void loadPublication(List<Journaliste> journalistes, List<TypePU> typePUS) {
        for (int i = 0; i < 200; i++) {
            publicationService.create(
                Publication.builder()
                    .groupePresse(randomInList(Constant.CONSTRAINT_GROUPE_PRESSE))
                    .periodicite(randomInList(Constant.CONSTRAINT_PERIODICITE))
                    .journaliste(randomInList(journalistes))
                    .typePU(randomInList(typePUS))
                .build()
            );
        }
    }

    private void loadNumero(List<Publication> publications) {
        for (Publication publication: publications) {
            for (int i = 0; i <= random.nextInt(1, 7); i++) {
                numeroService.create(
                    Numero.builder()
                        .publication(publication)
                    .build()
                );
            }
        }
    }

    private void loadRubrique(List<Journaliste> journalistes, List<Publication> publications) {
        for (int i = 0; i < 300; i++) {
            rubriqueService.create(
                Rubrique.builder()
                    .journaliste(randomInList(journalistes))
                    .publication(randomInList(publications))
                .build()
            );
        }
    }

    private void loadAbonner(List<Client> clients, List<Publication> publications) {
        clients.stream().filter(client -> client.getCode() % 7 != 0).forEach(client -> {
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

    private void loadCoutArticle(List<Rubrique> rubriques, List<Journaliste> journalistes) {
    }

    private void loadDiffuserPU(List<Publication> publications) {
        for (int annee = 2015; annee <= 2022; annee++) {
            for (Publication publication : publications) {
                diffuserPUService.create(
                    DiffuserPU.builder()
                        .id(DiffuserPUId.builder()
                            .publication(publication)
                            .annee(annee)
                        .build())
                        .nombreDiffusions(random.nextInt(40, 200))
                    .build()
                );
            }
        }
    }

    private void loadEcrireArticle(List<Journaliste> journalistes,
                                   List<Numero> numeros,
                                   List<Rubrique> rubriques) {
    }

    private void loadLivrer(List<Numero> numeros, List<Client> clients) {
        numeros.forEach(numero -> livrerService.create(
            Livrer.builder()
                .id(LivrerId.builder()
                    .client(randomInList(clients))
                    .numero(numero)
                .build())
            .build()
        ));
    }

    private void loadPayer(List<Publication> publications, List<Pays> pays) {

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
