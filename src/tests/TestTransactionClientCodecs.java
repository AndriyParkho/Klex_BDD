package tests;

import java.util.HashSet;

import model.Client;
import model.Codec;
import model.aggregates.ClientCodecs;
import transactions.TransactionClientCodecs;

public class TestTransactionClientCodecs {
    public static void main(String[] args) {

        Client client = new Client("Sony", "M4", 4000, 4000);
        HashSet<Codec> codecs = new HashSet<Codec>();
        codecs.add(new Codec("MPEG2", "audio"));
        codecs.add(new Codec("MPEG4", "video"));
        codecs.add(new Codec("Kate", "texte"));

        ClientCodecs clientCodecs = new ClientCodecs();
        clientCodecs.setClient(client);
        clientCodecs.setCodecs(codecs);

        TransactionClientCodecs.execute(clientCodecs);
    }
}
