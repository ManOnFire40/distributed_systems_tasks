package server;

import javax.xml.ws.Endpoint;

import server.WebServicesImpl;  

public class Publisher {
    public static void main(String[] args) {  
       Endpoint.publish("http://localhost:8888/rpc/testWebServices", new WebServicesImpl());  
    }  
}
