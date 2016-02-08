package de.ramnow.whopays.data;

import android.provider.BaseColumns;

/**
 * Defines table and column names for the who pays database.
 */
public class WhoPaysContract {

    /* Inner class that defines the table contents of the abrechnung table */
    public static final class AbrechnungEntry implements BaseColumns {

        public static final String TABLE_NAME = "ABRECHNUNG";
        public static final String COLUMN_NAME = "BEZEICHNUNG";
    }

    /* Inner class that defines the table contents of the person table */
    static final class PersonEntry implements BaseColumns {


        static final String TABLE_NAME = "PERSON";
        static final String COLUMN_NAME = "NAME";
    }

    /* Inner class that defines the table contents of the gruppe table */
    static final class GruppeEntry implements BaseColumns {

        static final String TABLE_NAME = "GRUPPE";
        static final String COLUMN_NAME = "BEZEICHNUNG";
    }

    /* Inner class that defines the table contents of the ausgaben table */
    static final class AusgabeEntry implements BaseColumns {

        static final String TABLE_NAME = "AUSGABE";
        static final String COLUMN_BESCHREIBUNG = "BESCHREIBUNG";
        static final String COLUMN_BETRAG = "BETRAG";

        // FKs
        static final String COLUMN_PERSON_KEY = "PERSON_ID";
        static final String COLUMN_GRUPPE_KEY = "GRUPPE_ID";
        static final String COLUMN_ABRECHNUNG_KEY = "ABRECHNUNG_ID";

    }

    /* Inner class that defines the link table between person and gruppe tables */
    static final class PersonInGruppeEntry implements BaseColumns {

        static final String TABLE_NAME = "PERSONEN_IN_GRUPPE";

        // FKs
        static final String COLUMN_PERSON_KEY = "PERSON_ID";
        static final String COLUMN_GRUPPE_KEY = "GRUPPE_ID";
    }

}
