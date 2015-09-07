/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ronny
 */
@ManagedBean
public class PerfilBean implements Serializable {
    
    private String text ="PrimeFaces";

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    
    
    
    
}
