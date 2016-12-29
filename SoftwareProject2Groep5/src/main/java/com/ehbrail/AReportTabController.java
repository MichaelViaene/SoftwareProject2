package com.ehbrail;

import com.database.TicketDAO;
import com.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by jorda on 25/11/2016.
 */
public class AReportTabController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private ResourceBundle language;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] months = DateFormatSymbols.getInstance(resources.getLocale()).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        setChartData();
    }

    public void setChartData() {
        int[] monthCounter = new int[12];
        List<Ticket> ticketList = TicketDAO.readTickets();
        for (Ticket ticket : ticketList) {
            int month = ticket.getDatumHeen().getMonthValue() - 1;
            monthCounter[month]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);
    }
}
