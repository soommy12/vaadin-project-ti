package com.ti.project.vaadin.vaadinprojectti;

/**
 * Created by Bartosz on 10.06.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name, email, phoneNumber, login, password FROM customers",
                (rs, rowNum) -> new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("login"),
                        rs.getString("password")));
    }

    public Customer find(String login, String password){
        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email, phoneNumber, login, password FROM customers WHERE login='"
                + login + "' AND password='" + password + "'", Customer.class);
    }

    public void create(Customer customer){
        jdbcTemplate.update(
                "INSERT INTO customers (first_name, last_name, email, phoneNumber, login, password) VALUES ('"+
                        customer.getFirstName() + "','"+ customer.getLastName()+ "','"+ customer.getEmail()+ "','" +
                        customer.getPhoneNumber() +"','" + customer.getLogin() + "','" + customer.getPassword()+"')");
    }
}