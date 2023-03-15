package client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import client.WebServicesInterface;

public class Client {
	
	  public static void main(String[] args) {

	        try {
	            //Refer to wsdl document
	            URL url = new URL("http://localhost:8888/rpc/testWebServices?wsdl");
	            
	            //Refer to wsdl document 
	            QName ServiceQname = new QName("http://server/", "WebServicesImplService");  

	            Service service = Service.create(url, ServiceQname);  
	            
	            QName WebServicesQname = new QName("http://server/", "WebServicesImplPort");  
 
	            WebServicesInterface services = service.getPort(WebServicesQname, WebServicesInterface.class);
	            
	            
	            System.out.println("the area that calculated at the server = " + services.GetTheRectangleArea(5, 5));
	            System.out.println("the profit that calculated at the server = " + services.get_profit(20,5, 5));

	            
	            //TODO call the get profit method from the server and pass (9000, 3000, 2000) as parameters then display the output
	            
	        } catch (MalformedURLException ex) {
	            System.out.println("WSDL document url error: " + ex);
	        }
	    } 
	
}


