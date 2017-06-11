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
public class LoginView extends VerticalLayout{

    @Autowired
    private CustomerService service;

    @Autowired
    private RegisterView registerView;

    private Customer customer;

    private TextField login;
    private PasswordField password;
    private Button loginBtn;
    private Button registerBtn;

    @PostConstruct
    protected void init() {
        login = new TextField("Login");
        password = new PasswordField("Password");
        loginBtn = new Button("Sign in");
        registerBtn = new Button("Register");
        HorizontalLayout buttons = new HorizontalLayout(loginBtn, registerBtn);
        loginBtn.addClickListener((Button.ClickListener) clickEvent -> login());
        registerBtn.addClickListener((Button.ClickListener) clickEvent -> register());
        this.addComponents(login, password, buttons);
        this.setComponentAlignment(login, Alignment.TOP_CENTER);
        this.setComponentAlignment(password, Alignment.TOP_CENTER);
        this.setComponentAlignment(buttons, Alignment.TOP_CENTER);
    }

    private void login(){
        customer = service.find(login.getValue(), password.getValue());
        if(customer==null){
            Window popup = new Window();
            popup.setContent(new Label("User not found!"));
            popup.setVisible(true);
            popup.center();
            getUI().addWindow(popup);
        } else {
            getUI().setContent(new BookView(customer));
        }
    }
    private void register(){
        Window popup = new Window();
        popup.setContent(registerView);
        popup.setVisible(true);
        popup.center();
        getUI().addWindow(popup);
    }
}
