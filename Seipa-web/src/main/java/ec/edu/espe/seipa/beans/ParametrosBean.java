/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Datosformulas;
import ec.edu.espe.seipa.service.DatosFormulaServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class ParametrosBean extends BotonesBean implements Serializable {

    @EJB
    private DatosFormulaServicio datosFormulaServicio;

    private List<Datosformulas> parametros;

    private String idDatoFormula;

    private Datosformulas datoSeleccionado;
    private Datosformulas datoFormula;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.parametros = this.datosFormulaServicio.obtener();

    }

    public void filaSeleccionada(ActionEvent evento) {
        if (datoSeleccionado instanceof Datosformulas) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.datoFormula = new Datosformulas();
        MensajesGenericos.infoCancelar();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.datoFormula = new Datosformulas();
    }

    public void modificar(ActionEvent evento) {
        this.datoFormula = new Datosformulas();
        try {
            this.datoFormula = (Datosformulas) BeanUtils.cloneBean(this.datoSeleccionado);
            this.datosFormulaServicio.actualizar(this.datoFormula);
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        this.datosFormulaServicio.eliminar(this.datoSeleccionado);
        this.parametros.remove(this.datoSeleccionado);
        MensajesGenericos.infoEliminar("Parametros", this.datoFormula.getParametro().concat(" - Eliminado con éxito "), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void guardar(ActionEvent evento) {
        try {
            char a = '1';
            if (this.datoFormula == null) {
                this.datoFormula.setIddatosformula(new BigDecimal(datosFormulaServicio.idDatosFormula()).add(new BigDecimal("1")));
            }
            if (super.getEnRegistro()) {
                super.crear();
                this.datoFormula = new Datosformulas();
                this.datoFormula.setEstadoparametro(a);
                this.datosFormulaServicio.crear(this.datoFormula);
                this.parametros.add(this.datoFormula);
                MensajesGenericos.infoCrear("Parametro Formula", this.datoFormula.getIddatosformula().toString().concat(" - Creada con éxito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.parametros.indexOf(this.datoFormula);
                this.datosFormulaServicio.actualizar(this.datoFormula);
                parametros.set(i, this.datoFormula);
                MensajesGenericos.infoModificar("Parametro Formula", this.datoFormula.getIddatosformula().toString().concat(" - Modificada con éxito "), Boolean.TRUE);
                super.sinSeleccion();
            }

        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public DatosFormulaServicio getDatosFormulaServicio() {
        return datosFormulaServicio;
    }

    public void setDatosFormulaServicio(DatosFormulaServicio datosFormulaServicio) {
        this.datosFormulaServicio = datosFormulaServicio;
    }

    public Datosformulas getDatoSeleccionado() {
        return datoSeleccionado;
    }

    public void setDatoSeleccionado(Datosformulas datoSeleccionado) {
        this.datoSeleccionado = datoSeleccionado;
    }

    public Datosformulas getDatoFormula() {
        return datoFormula;
    }

    public void setDatoFormula(Datosformulas datoFormula) {
        this.datoFormula = datoFormula;
    }

    public List<Datosformulas> getParametros() {
        return parametros;
    }

    public void setParametros(List<Datosformulas> parametros) {
        this.parametros = parametros;
    }

    public String getIdDatoFormula() {
        return idDatoFormula;
    }

    public void setIdDatoFormula(String idDatoFormula) {
        this.idDatoFormula = idDatoFormula;
    }

}
