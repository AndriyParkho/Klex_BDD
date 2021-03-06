package model.aggregates;

import java.util.HashSet;

import model.Client;
import model.Codec;

public class ClientCodecs {
    private Client client;
    private HashSet<Codec> codecs = new HashSet<Codec>();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashSet<Codec> getCodecs() {
        return codecs;
    }

    public void setCodecs(HashSet<Codec> codecs) {
        this.codecs = codecs;
    }

	@Override
	public String toString() {
		return "ClientCodecs [client=" + client + ", codecs=" + codecs + "]";
	}
    
    
}
