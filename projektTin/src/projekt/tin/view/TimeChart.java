package projekt.tin.view;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeChart {
	public ChartPanel addData(List<Double> oneDayCallsInQuarter, Integer startFrom, Integer endIn) {
		TimeSeries quartersSeries = new TimeSeries("Natê¿enie ruchu");
		
		Minute time = new Minute();
		if (startFrom == null || endIn == null) {
			int minute = 0;
			int hour = 0;
			for (Double myQuarter : oneDayCallsInQuarter) {
				time = new Minute(minute,hour,1,12,2015);
				quartersSeries.add(time, myQuarter);
				if (minute == 45) {
					minute = 0;
					hour++;
				} else {
					minute += 15;
				}
			}
		} else {
			int minute = startFrom;
			while (minute % 15 != 0) {
				minute++;
			}
			int hour;
			
			if (minute < 59) {
				hour = 0;
			} else {
				hour = minute / 60;
				minute = hour % 60;
				while (minute % 15 != 0) {
					minute--;
				}
			}
			
			int i = 0;
			for (Double myQuarter : oneDayCallsInQuarter) {
				if ((i  >= startFrom / 15) && (i  <= endIn / 15)) {
					time = new Minute(minute,hour,1,12,2015);
					quartersSeries.add(time, myQuarter);
					if (minute == 45) {
						minute = 0;
						hour++;
					} else {
						minute += 15;
					}
				}
				i ++;
			}
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(quartersSeries);

		JFreeChart chart = ChartFactory.createTimeSeriesChart("Natê¿enie ruchu", "Godzina", "Natê¿enie", dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		
		return chartPanel;
	}
	
	public ChartPanel barChart(List<Double> callsList) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int day = 1;
		
		for (Double myDay : callsList) {
			dataset.setValue(myDay, "Natê¿enie", Integer.toString(day));
			day++;
		}
		
		JFreeChart chart = ChartFactory.createBarChart("Natê¿enie ruchu", "Dzieñ", "Natê¿enie", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		return chartPanel;
	}

}
