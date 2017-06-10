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
    CustomerService service;

    private TextField login;
    private PasswordField password;
    private Button loginBtn;
    private Button registerBtn;

    protected void init() {
        login = new TextField("Login");
        password = new PasswordField("Has³o");
        loginBtn = new Button("Loguj");
        registerBtn = new Button("Rejestruj");
        loginBtn.addClickListener((Button.ClickListener) clickEvent -> login());
        registerBtn.addClickListener((Button.ClickListener) clickEvent -> register());
        this.addComponents(login, password, loginBtn, registerBtn);
    }

    private void login(){
        Customer customer = service.find(login.getValue(), password.getValue());
        if(customer==null){
            Window popup = new Window();
            popup.setContent(new Label("Nie znaleziono uzytkownika!"));
            popup.setVisible(true);
            popup.center();
            getUI().addWindow(popup);
        } else {
            getUI().setContent(new VaadinUI());
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
