/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.DatosformulasFacade;
import ec.edu.espe.seipa.model.Datosformulas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ronny
 */
@Stateless
@LocalBean
public class DatosFormulaServicio {

    @EJB
    private DatosformulasFacade datosFormulaFacade;

    public List<Datosformulas> obtener() {
        return this.datosFormulaFacade.findAll();

    }

    public void crear(Datosformulas datos) {
        this.datosFormulaFacade.create(datos);
    }

    public void actualizar(Datosformulas datos) {
        this.datosFormulaFacade.edit(datos);
    }

    public void eliminar(Datosformulas datos) {
        this.datosFormulaFacade.remove(datos);
    }

    public String idDatosFormula() {
        return this.datosFormulaFacade.findIdDatosFormula();
    }

}
