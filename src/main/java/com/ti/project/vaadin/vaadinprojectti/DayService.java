package com.ti.project.vaadin.vaadinprojectti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bartosz on 10.06.2017.
 */
@Component
public class DayService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Day> findAll(String dayName) {
        return jdbcTemplate.query(
                "SELECT hourtoreserve, reservedon FROM " + dayName,
                (rs, rowNum) -> new Day(
                        rs.getString("hourtoreserve"),
                        rs.getString("reservedon")));
    }

    public void update(Day day, String dayName){
        jdbcTemplate.update(
                "UPDATE " + dayName + " SET reservedon=?",
                day.getReservedOn());
    }
}
