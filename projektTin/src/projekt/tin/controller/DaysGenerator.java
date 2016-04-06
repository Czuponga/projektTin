package projekt.tin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DaysGenerator {
	public List<List> generateDays(List<Double> firstDay) {
		int i;
		double temp;
		List<List> daysList = new ArrayList<>();
		daysList.add(firstDay);
		for (int j = 0; j < 28; j++) {
			i = 1;
			List<Double> list = new ArrayList<>();
			for (double quarter : firstDay) {
				double newQuarter = 0;
				if ((i >= 1 && i < 28) || (i >= 83)) {
					newQuarter = ThreadLocalRandom.current().nextDouble(
							(quarter - 0.15), (quarter + 0.15));
					list.add(newQuarter);
				}
				else if ((i >= 28 && i < 32) || (i >= 68 && i < 83)) {
					newQuarter = ThreadLocalRandom.current().nextDouble(
							(quarter - 10), (quarter + 10));
					list.add(newQuarter);
				}
				else if (i >= 32 && i < 68) {
					newQuarter = ThreadLocalRandom.current().nextDouble(
							(quarter - 50), (quarter + 50));
					list.add(newQuarter);
				}
				i++;
			}
			daysList.add(list);
		}
		return daysList;
	}
}
