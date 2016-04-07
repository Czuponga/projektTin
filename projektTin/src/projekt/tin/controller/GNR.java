package projekt.tin.controller;

import java.util.ArrayList;
import java.util.List;

public class GNR {

//	private int firstQuarterOfGNR;
//	private int callsInGNR;
	private List<Double> averagedDay;

	public void averageDay(List<List> thirtyDaysCallsInQuarters) {
		// List<Double> averagedDay = new ArrayList<>();
		double sum = 0;
		double average = 0;
		for (int j = 0; j < thirtyDaysCallsInQuarters.get(0).size(); j++) {
			for (int i = 0; i < thirtyDaysCallsInQuarters.size(); i++) {
				sum += (double) thirtyDaysCallsInQuarters.get(i).get(j);
			}
			average = sum / thirtyDaysCallsInQuarters.size();
			averagedDay.add(average);
			sum = 0;
		}
	}
	
	public String findGNR(List<Double> oneDayCallsInQuarter){
		double max = 0;
		double sum = 0;
		int indexOfFirstQuarterInGNR = 0;
		double callsInGNR;
		for (int i = 0; i < oneDayCallsInQuarter.size() - 4; i++) {
			for (int j = i; j < i + 4; j++) {
				sum += oneDayCallsInQuarter.get(j);
			}
			if (sum > max) {
				max = sum;
				indexOfFirstQuarterInGNR = i;
			}
			sum = 0;
		}
		callsInGNR = max;

		String result;
		result = String.valueOf(indexOfFirstQuarterInGNR) + " "
				+ String.valueOf(callsInGNR);

		return result;
	}

	public String methodTCBH(List<List> thirtyDaysCallsInQuarters) {
		String result;
		averageDay(thirtyDaysCallsInQuarters);
		result = findGNR(averagedDay);
		
		return result;
	}
	
	

}
