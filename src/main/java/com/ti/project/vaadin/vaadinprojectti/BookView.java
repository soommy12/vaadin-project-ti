package com.ti.project.vaadin.vaadinprojectti;

/**
 * Created by Bartosz on 10.06.2017.
 */
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SpringUI
//public class VaadinUI extends UI {
public class BookView extends HorizontalLayout implements View {

    private TabSheet tabSheet = new TabSheet();

    @Autowired
    private DayService service;

    private Day day;
    private Binder<Day> binder = new Binder<>(Day.class);


    private TextField hourtoreserve = new TextField("Hour to book");
    private TextField reservedon = new TextField("Reserved by");
    private Button save = new Button("Book");


    //@Override
    protected void init(/*VaadinRequest request*/) {

        binder.bindInstanceFields(this);
        setTabSheet();
        VerticalLayout bookLayout = new VerticalLayout(hourtoreserve, reservedon, save);
//        HorizontalLayout layout = new HorizontalLayout(tabSheet, bookLayout);
        this.addComponents(tabSheet, bookLayout);
    }

    private void setFormVisible(boolean visible) {
        hourtoreserve.setVisible(visible);
        reservedon.setVisible(visible);
        save.setVisible(visible);
    }


    private void book(String weekDay, String hour) {
        service.update(day, weekDay, hour);
        Grid<Day> grid = new Grid(Day.class);
        List<Day> days = service.findAll(weekDay);
        grid.setItems(days);
    }

    private void setTabSheet() {
        addTabDay("monday");
        addTabDay("tuesday");
        addTabDay("wednesday");
        addTabDay("thursday");
        addTabDay("friday");
        addTabDay("saturday");
        addTabDay("sunday");
    }

    private void addTabDay(String weekDay) {
        Grid<Day> grid = new Grid(Day.class);
        List<Day> days = service.findAll(weekDay);
        grid.setItems(days);
        setFormVisible(false);
        grid.setColumns("hourToBook", "reservedOn");
        grid.addSelectionListener(e -> {
            if (grid.asSingleSelect().isEmpty()) {
                setFormVisible(false);
            } else {
                day = grid.asSingleSelect().getValue();
                binder.setBean(day);
                hourtoreserve.setValue(grid.asSingleSelect().getValue().getHourToBook());
                hourtoreserve.setEnabled(false);
                save.addClickListener(clickEvent -> book(weekDay, grid.asSingleSelect().getValue().getHourToBook()));
                setFormVisible(true);
            }
        });
        VerticalLayout dayLayout = new VerticalLayout(grid);
        tabSheet.addTab(dayLayout, weekDay);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        init();
    }
}
