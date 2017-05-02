/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javamailapp.JavaMailApp;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
/**
 *
 * @author it353s723
 */
@FacesConverter("CustomCalendarConverter")
public class CalConverter implements Converter{

@Override
public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if(value != null && !value.toString().isEmpty()){
        try {
            String pattern = (String) component.getAttributes().get("pattern");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat(pattern).parse(value));
            return calendar;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    return null;
}

@Override
public String getAsString(FacesContext context, UIComponent component, Object value) {

    if(value != null && !value.toString().isEmpty()){
        Calendar cal = (Calendar) value;
        String pattern = (String) component.getAttributes().get("pattern");
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }
        else return null;
    }
}
