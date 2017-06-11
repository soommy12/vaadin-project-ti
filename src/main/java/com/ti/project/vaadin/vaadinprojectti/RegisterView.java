package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by przem on 10.06.2017.
 */
@SpringView(name = "RegisterView")
public class RegisterView extends VerticalLayout implements View {

    @Autowired
    CustomerService service;

    private TextField login;
    private TextField password;
    private TextField firstname;
    private TextField lastname;
    private TextField email;
    private TextField phone;
    private Button registerBtn;
    private Button cancelBtn;

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
        service.create(customer);
        Window popup = new Window();
        VerticalLayout popupLayout = new VerticalLayout();
        popupLayout.addComponents(new Label("Account created!"), new Button("OK",
                (Button.ClickListener) clickEvent -> getUI().setContent(new LoginView())));
        popup.setContent(popupLayout);
    }

    private void cancel(){
        getUI().setContent(new LoginView());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        init();
    }

    RegisterView(){
        init();
    }
}
