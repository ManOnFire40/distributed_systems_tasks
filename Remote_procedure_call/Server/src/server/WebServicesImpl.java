package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

import server.WebServicesInterface;

@WebService(endpointInterface = "server.WebServicesInterface")
public class WebServicesImpl implements WebServicesInterface {

	@Override
	public int GetTheRectangleArea(int width, int height) {
		int area = width * height;
		return area;
	}

	@Override
	public int get_profit(int sales, int labor_cost, int food_cost) {
		return sales - labor_cost - food_cost;
	}

	// TODO implement get profit method to calculate the profit as following profit
	// = sales - (labor cost + food cost)

}
