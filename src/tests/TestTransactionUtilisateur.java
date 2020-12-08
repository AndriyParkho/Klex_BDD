package tests;

import model.Utilisateur;
import transactions.TransactionUtilisateur;

public class TestTransactionUtilisateur {
    public static void main(String[] args) {
        Utilisateur utilisateur = new Utilisateur("@gmail.com", "Theo", "Manfredi", 21, "Français", 1234);
        TransactionUtilisateur.execute(utilisateur);
    }
}
