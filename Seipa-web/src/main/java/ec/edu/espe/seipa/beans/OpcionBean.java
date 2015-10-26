/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.service.OpcionServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class OpcionBean extends BotonesBean implements Serializable {

    private List<Opcion> opciones;
    private Opcion opcion;

    private String opcionTexto;
    private String opcionValor;
    private Integer idOpcion;

    @EJB
    private OpcionServicio opcionServicio;

    public OpcionBean() {
        opciones = new ArrayList();

        opciones.add(new Opcion());
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.opcion = new Opcion();
        opciones = new ArrayList<Opcion>();
        encerar();
    }

    public void agregarOpciones() {
        this.opcion.setDescripcion(opcionTexto);
        this.opcion.setValorOpcion(opcionValor);
        this.opcion.setIdopcion(BigDecimal.valueOf(idOpcion));
//        if (opciones == null) {
//            opciones = new ArrayList<Opcion>();
//        }
        this.opciones.add(opcion);
        this.opcion = new Opcion();
    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.opcionServicio.crear(this.opcion);
                this.getOpciones().add(this.opcion);
                //MensajesGenericos.infoCrear("Docente", this.docente.getId().concat(" - ").concat(this.docente.getNombre()).concat(" ").concat(this.docente.getApellido()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.getOpciones().indexOf(this.opcion);
                this.opcionServicio.actualizar(this.opcion);
                getOpciones().set(i, this.opcion);
                //MensajesGenericos.infoModificar("Usuario", this.docente.getId().concat(" - ").concat(this.docente.getNombre()).concat(" ").concat(this.docente.getApellido()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }
    
    public void encerar(){
        opcionTexto="";
        opcionValor="";
        idOpcion= 1;
        opciones = new ArrayList<Opcion>();
    }


    public void onButtonRemoveOpcionClick(final Opcion p_Opcion) {
        getOpciones().remove(p_Opcion);
    }

    public void onButtonAddOpcionClick(AjaxBehaviorEvent p_oEvent) {
        getOpciones().add(new Opcion());
    }

    /**
     * @return the opcion
     */
    public Opcion getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    /**
     * @return the opcionTexto
     */
    public String getOpcionTexto() {
        return opcionTexto;
    }

    /**
     * @param opcionTexto the opcionTexto to set
     */
    public void setOpcionTexto(String opcionTexto) {
        this.opcionTexto = opcionTexto;
    }

    /**
     * @return the opcionValor
     */
    public String getOpcionValor() {
        return opcionValor;
    }

    /**
     * @param opcionValor the opcionValor to set
     */
    public void setOpcionValor(String opcionValor) {
        this.opcionValor = opcionValor;
    }

    /**
     * @return the opciones
     */
    public List<Opcion> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    /**
     * @return the idOpcion
     */
    public Integer getIdOpcion() {
        return idOpcion;
    }

    /**
     * @param idOpcion the idOpcion to set
     */
    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }
}
