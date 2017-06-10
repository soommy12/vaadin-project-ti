package com.ti.project.vaadin.vaadinprojectti;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by przem on 10.06.2017.
 */
@SpringUI
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setContent(new LoginView());
    }
}
