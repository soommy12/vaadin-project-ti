package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by przem on 10.06.2017.
 */

@SpringComponent
@Scope("singleton") //zeby miec dokladnie jedna instancje tej klasy
public class RegisterView extends VerticalLayout{

    @Autowired
    CustomerService customerService;

    private TextField login;
    private TextField password;
    private TextField firstname;
    private TextField lastname;
    private TextField email;
    private TextField phone;
    private Button registerBtn;
    private Button cancelBtn;

    @PostConstruct
    protected void init() {
        login = new TextField("Login");
        password = new TextField("Password");
        firstname = new TextField("First name");
        lastname = new TextField("Last name");
        email = new TextField("Email");
        phone = new TextField("Telephone");
        registerBtn = new Button("Register");
        cancelBtn = new Button("Cancel");
        this.addComponents(login, password, firstname, lastname, email, phone, registerBtn);
        registerBtn.addClickListener((Button.ClickListener) clickEvent -> register());
        cancelBtn.addClickListener((Button.ClickListener) clickEvent -> cancel());
    }

    private void register(){
        Customer customer = new Customer(firstname.getValue(), lastname.getValue(), email.getValue(), phone.getValue(),
                login.getValue(), password.getValue());
        System.out.println(customer);
        customerService.create(customer);
        VerticalLayout popupLayout = new VerticalLayout();
        popupLayout.addComponents(new Label("Account created!"), new Button("OK",
                (Button.ClickListener) clickEvent -> getUI().removeWindow((Window)getParent())));
        this.removeAllComponents();
        this.addComponent(popupLayout);
    }

    private void cancel(){
        getUI().setContent(new LoginView());
    }
}
