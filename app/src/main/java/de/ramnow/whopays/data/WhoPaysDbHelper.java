package de.ramnow.whopays.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.ramnow.whopays.data.WhoPaysContract.PersonEntry;
import de.ramnow.whopays.data.WhoPaysContract.GruppeEntry;
import de.ramnow.whopays.data.WhoPaysContract.AusgabeEntry;
import de.ramnow.whopays.data.WhoPaysContract.AbrechnungEntry;
import de.ramnow.whopays.data.WhoPaysContract.PersonInGruppeEntry;

/**
 * Manages a local database for who pays data.
 */
public class WhoPaysDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "whopays.db";

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public WhoPaysDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // ABRECHNUNG
        final String SQL_CREATE_ABRECHNUNG_TABLE = "CREATE TABLE " + AbrechnungEntry.TABLE_NAME + " (" +
                AbrechnungEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AbrechnungEntry.COLUMN_NAME + " TEXT NOT NULL); ";

        sqLiteDatabase.execSQL(SQL_CREATE_ABRECHNUNG_TABLE);

        // PERSON
        final String SQL_CREATE_PERSON_TABLE = "CREATE TABLE " + PersonEntry.TABLE_NAME + " (" +
                PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PersonEntry.COLUMN_NAME + " TEXT NOT NULL); ";

        sqLiteDatabase.execSQL(SQL_CREATE_PERSON_TABLE);

        // GRUPPE
        final String SQL_CREATE_GRUPPE_TABLE = "CREATE TABLE " + GruppeEntry.TABLE_NAME + " (" +
                GruppeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GruppeEntry.COLUMN_NAME + " TEXT NOT NULL); ";

        sqLiteDatabase.execSQL(SQL_CREATE_GRUPPE_TABLE);

        // PERSON_IN_GRUPPE
        final String SQL_CREATE_PERSON_IN_GRUPPE_TABLE = "CREATE TABLE " + PersonInGruppeEntry.TABLE_NAME + " (" +
                PersonInGruppeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PersonInGruppeEntry.COLUMN_PERSON_KEY + " INTEGER NOT NULL, " +
                PersonInGruppeEntry.COLUMN_GRUPPE_KEY + " INTEGER NOT NULL, " +

                // Set up the person column as a foreign key to person table.
                " FOREIGN KEY (" + PersonInGruppeEntry.COLUMN_PERSON_KEY + ") REFERENCES " +
                PersonEntry.TABLE_NAME + " (" + PersonEntry._ID + "), " +

                // Set up the gruppe column as a foreign key to gruppe table.
                " FOREIGN KEY (" + PersonInGruppeEntry.COLUMN_GRUPPE_KEY + ") REFERENCES " +
                GruppeEntry.TABLE_NAME + " (" + GruppeEntry._ID + ")); ";

        sqLiteDatabase.execSQL(SQL_CREATE_PERSON_IN_GRUPPE_TABLE);

        // AUSGABE
        final String SQL_CREATE_AUSGABE_TABLE = "CREATE TABLE " + AusgabeEntry.TABLE_NAME + " (" +
                AusgabeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                AusgabeEntry.COLUMN_BESCHREIBUNG + " TEXT NOT NULL, " +
                AusgabeEntry.COLUMN_BETRAG + " REAL NOT NULL, " +

                AusgabeEntry.COLUMN_PERSON_KEY + " INTEGER NOT NULL, " +
                AusgabeEntry.COLUMN_GRUPPE_KEY + " INTEGER NOT NULL, " +
                AusgabeEntry.COLUMN_ABRECHNUNG_KEY + " INTEGER NOT NULL, " +

                // Set up the person column as a foreign key to person table.
                " FOREIGN KEY (" + AusgabeEntry.COLUMN_PERSON_KEY + ") REFERENCES " +
                PersonEntry.TABLE_NAME + " (" + PersonEntry._ID + "), " +

                // Set up the gruppe column as a foreign key to gruppe table.
                " FOREIGN KEY (" + AusgabeEntry.COLUMN_GRUPPE_KEY + ") REFERENCES " +
                GruppeEntry.TABLE_NAME + " (" + GruppeEntry._ID + "), " +

                // Set up the abrechnung column as a foreign key to abrechnung table.
                " FOREIGN KEY (" + AusgabeEntry.COLUMN_ABRECHNUNG_KEY + ") REFERENCES " +
                AbrechnungEntry.TABLE_NAME + " (" + AbrechnungEntry._ID + ")); ";

        sqLiteDatabase.execSQL(SQL_CREATE_AUSGABE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // For now simply drop the tables and create a new ones. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AusgabeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PersonInGruppeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PersonEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GruppeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AbrechnungEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
