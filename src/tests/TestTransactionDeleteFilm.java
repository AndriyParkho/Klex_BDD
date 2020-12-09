package tests;

import java.sql.Date;

import transactions.TransactionDeletes;

public class TestTransactionDeleteFilm {
    public static void main(String[] args) {
        TransactionDeletes.deleteFilm("Le Dernier Voeux", Date.valueOf("2015-11-23"));
    }
}
