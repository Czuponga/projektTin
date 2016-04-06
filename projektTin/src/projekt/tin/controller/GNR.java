package projekt.tin.controller;

import java.util.ArrayList;
import java.util.List;

public class GNR {

	public List<Double> averageDay(List<List> thirtyDaysCallsInQuarters) {
		List<Double> averagedDay = new ArrayList<>();
		double sum = 0;
		double average = 0;
		int j = 0;
		// for(int j=0)
		for (int i = 0; i < thirtyDaysCallsInQuarters.size(); i++) {
			sum = (double) thirtyDaysCallsInQuarters.get(i).get(j);
		}
		average = sum / thirtyDaysCallsInQuarters.size();
		averagedDay.add(average);
		j++;

		return averagedDay;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
