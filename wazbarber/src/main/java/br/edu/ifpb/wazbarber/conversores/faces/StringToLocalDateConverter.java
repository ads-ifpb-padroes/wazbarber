/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.conversores.faces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author romulo
 */
@FacesConverter(value = "convert.StringToLocalDate", forClass = LocalDate.class)
public class StringToLocalDateConverter implements Converter{
    
    DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null){
            return null;
        }
        
        return LocalDate.parse(value, data);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        
        LocalDate date = (LocalDate) value;
        
        return date.format(data);
    }
    
}
