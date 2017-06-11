package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;

/**
 * Created by przem on 10.06.2017.
 */
//@SpringView(name = "LoginView")

@SpringComponent
@Scope("singleton") //zeby miec dokladnie jedna instancje tej klasy
public class LoginView extends VerticalLayout{

    @Autowired
    private CustomerService service;

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
//        service.find(login.getValue(), password.getValue());
//        customer = service.find("a", "a");
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
        popup.setContent(new RegisterView());
        popup.setVisible(true);
        popup.center();
        getUI().addWindow(popup);
    }

   /* @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //init();
    }*/

   /* LoginView(){
        init();
    }*/
}
