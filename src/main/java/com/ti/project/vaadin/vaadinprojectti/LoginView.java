package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by przem on 10.06.2017.
 */
@SpringView(name = "LoginView")
public class LoginView extends VerticalLayout implements View{

    @Autowired
    private CustomerService service;

    @Autowired
    private  DayService service2;
    private Customer customer;

    private TextField login;
    private PasswordField password;
    private Button loginBtn;
    private Button registerBtn;

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
        //service.find(login.getValue(), password.getValue());
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        init();
    }

    LoginView(){
        init();
    }
}
