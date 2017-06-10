package com.ti.project.vaadin.vaadinprojectti;

/**
 * Created by Bartosz on 10.06.2017.
 */
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SpringUI
public class VaadinUI extends UI {

    private TabSheet tabSheet = new TabSheet();

    @Autowired
    private DayService service;

    private Day day;
    private Binder<Day> binder = new Binder<>(Day.class);

    private Grid<Day> grid = new Grid(Day.class);
    private TextField hourtoreserve = new TextField("Hour of reservtion");
    private TextField reservedon = new TextField("Reserved by");
    //private Button save = new Button("Book", e -> reserve()); //tu bedzie button do rejstracji


    @Override
    protected void init(VaadinRequest request) {


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        setTabSheet(day);


        Grid<Day> grid = new Grid(Day.class);
        grid.setColumns("Hour", "Reserved by");
        updateGrid();
        binder.bindInstanceFields(this);
        VerticalLayout layout = new VerticalLayout(grid);
        setContent(layout);
    }

    private void updateGrid() {
        List<Day> days = service.findAll("monday");
        grid.setItems(days);
        setFormVisible(false);
    }

    private void setFormVisible(boolean visible) {
        hourtoreserve.setVisible(visible);
        reservedon.setVisible(visible);
        //save.setVisible(visible);
    }

    private void setTabSheet(int day) {

        Grid<Day> grid = new Grid(Day.class);
        grid.setColumns("Hour", "Reserved by");
        updateGrid();
        binder.bindInstanceFields(this);
        VerticalLayout monday = new VerticalLayout(grid);
        VerticalLayout tuesday = new VerticalLayout();
        VerticalLayout wednesday = new VerticalLayout();
        VerticalLayout thursday = new VerticalLayout();
        VerticalLayout friday = new VerticalLayout();
        VerticalLayout saturday = new VerticalLayout();
        VerticalLayout sunday = new VerticalLayout();
        tabSheet.addTab(monday, "Monday");
        tabSheet.addTab(tuesday, "Tuesday");
        tabSheet.addTab(wednesday, "Wednesday");
        tabSheet.addTab(thursday, "Thursday");
        tabSheet.addTab(friday, "Friday");
        tabSheet.addTab(saturday, "Saturday");
        tabSheet.addTab(sunday, "Sunday");
    }
}
