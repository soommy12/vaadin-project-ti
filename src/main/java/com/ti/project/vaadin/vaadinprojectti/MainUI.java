package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


/**
 * Created by przem on 10.06.2017.
 */

@SpringUI
public class MainUI extends UI {

    @Autowired
    LoginView loginView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(loginView);
    }
}
