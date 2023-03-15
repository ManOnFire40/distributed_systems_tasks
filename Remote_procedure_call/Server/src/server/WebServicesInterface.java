package server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServicesInterface {
	@WebMethod int GetTheRectangleArea(int width, int height);  
	@WebMethod int get_profit(int sales, int labor_cost,int food_cost);  
 //TODO create get profit interface that takes sales, labor cost, and food cost as an input and return profit as an output
    
}
