/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class ReportesBean {

    /**
     * Creates a new instance of ReportesBean
     */
    private Docente docentereporte;
    
    public ReportesBean() {
        docentereporte = (Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente");
    }

    public void GenerarReporte() {
        File JasperDocSum = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/DocenteSumario.jasper"));
        
    }

}
