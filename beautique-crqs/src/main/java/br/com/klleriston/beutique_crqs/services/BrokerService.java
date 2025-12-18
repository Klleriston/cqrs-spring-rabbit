package br.com.klleriston.beutique_crqs.services;

public interface BrokerService {
    public void send(String type, Object data);
}
