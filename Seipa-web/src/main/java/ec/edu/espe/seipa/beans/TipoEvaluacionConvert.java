/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ronny
 */
@FacesConverter("tipoEvaluacionConverter")
public class TipoEvaluacionConvert implements Converter {
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                TipoEvaluacionServicio service = (TipoEvaluacionServicio) fc.getExternalContext().getApplicationMap().get("tipoEvaluacionServicio");
                return service.obtener().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid type Evaluation."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((TipoEvaluacion) object).getIdtipoevaluacion());
        }
        else {
            return null;
        }
    }   
    
}
