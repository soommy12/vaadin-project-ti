package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.io.File;

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

    @Autowired
    private BookView bookView;

    private Customer customer;

    private TextField login;
    private PasswordField password;
    private Button loginBtn;
    private Button registerBtn;
    private Label welcomeLabel;
  //  private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

    @PostConstruct
    protected void init() {
        login = new TextField("Login");
        password = new PasswordField("Password");
        loginBtn = new Button("Sign in");
        registerBtn = new Button("Register");
        welcomeLabel = new Label("Welcome to the ORLIK Booking!");

        FileResource resource = new FileResource(new File("C:\\Users\\Bartosz\\Documents\\STUDIA\\vaadin-project-ti\\src\\main\\resources\\football-1.png"));
        Image footballImage = new Image("",resource);
        HorizontalLayout buttons = new HorizontalLayout(loginBtn, registerBtn);
        loginBtn.addClickListener((Button.ClickListener) clickEvent -> login());
        registerBtn.addClickListener((Button.ClickListener) clickEvent -> register());
        this.addComponents(welcomeLabel, login, password, buttons, footballImage);
        this.setComponentAlignment(welcomeLabel, Alignment.TOP_CENTER);
        this.setComponentAlignment(footballImage, Alignment.TOP_CENTER);
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
            bookView.setCustomer(customer);
            getUI().setContent(bookView);
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
