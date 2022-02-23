package fr.ut1.miage.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Constant {

    public static final String CONSTRAINT_NOMIF_STR =
            "\"centre de formation des journalistes\"," +
            "\"centre universitaire d'enseignement du journalisme\"," +
            "\"ecole des hautes etudes en sciences de l'information et de la communication\"," +
            "\"institut universitaire de technologie de Lannion\"";
    public static final String CONSTRAINT_NOMTYJ_STR =
            "\"billettiste\",\"critique littéraire\",\"correspondant\",\"correspondant de guerre\",\"dessinateur de presse\"," +
            "\"éditorialiste\",\"feuilletoniste\",\"fixeur\",\"journaliste reporter d'images\",\"présentateur de journal\"," +
            "\"photojournaliste\",\"rédacteur en chef\",\"reporter\",\"secrétaire de rédaction\"";
    public static final String CONSTRAINT_PERIODICITE_STR =
            "\"quotidien\",\"bihebdomadaire\",\"hebdomadaire\",\"bimensuel\"," +
            "\"mensuel\",\"bimestriel\",\"trimestriel\",\"quadrimestriel\"," +
            "\"semestriel\",\"annuel\",\"biennal\"";
    public static final String CONSTRAINT_NOMTY_STR =
            "\"Généraliste\",\"Automobile\",\"Télévision\",\"Actualités\"," +
            "\"Voyages\",\"Vulgarisation Scientifique\",\"Féminin\"";

    public static final List<String> CONSTRAINT_NOMIF = Arrays.stream(CONSTRAINT_NOMIF_STR.split(","))
            .map(s -> s.substring(1, s.length() - 1))
            .collect(Collectors.toList());
    public static final List<String> CONSTRAINT_NOMTYJ = Arrays.stream(CONSTRAINT_NOMTYJ_STR.split(","))
            .map(s -> s.substring(1, s.length() - 1))
            .collect(Collectors.toList());

}
