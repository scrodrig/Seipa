 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.model.TipoPregunta;
import ec.edu.espe.seipa.service.OpcionServicio;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import ec.edu.espe.seipa.service.TipoPreguntaServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class CreacionPreguntasBean extends BotonesBean implements Serializable {

    @EJB
    private TipoEvaluacionServicio tipoEvaluacionServicio;
    @EJB
    private TipoPreguntaServicio tipoPreguntaServicio;

    @EJB
    private OpcionServicio opcionServicio;

    private String tipoEvaluacion;
    private String idTipoEvaluacion;
    private List<TipoEvaluacion> tipoEvaluaciones;
    
    private List<Pregunta> preguntas;

    private String tipoPregunta;
    private List<TipoPregunta> tipoPreguntas;

    private List<Opcion> opciones;
    private Opcion opcion;

    private Pregunta pregunta;
    private String textoPregunta;
    private String opcionTexto;
    private String opcionValor;
    private Integer idOpcion;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.tipoEvaluaciones = tipoEvaluacionServicio.obtener();
        this.tipoPreguntas = tipoPreguntaServicio.obtener();

    }
    
    public void nuevaPregunta(ActionEvent evento){
        super.crear();
        this.pregunta = new Pregunta();
        this.preguntas = new ArrayList<Pregunta>();
        this.idTipoEvaluacion = getTipoEvaluacion();
        System.out.println(idTipoEvaluacion.length());
    }

    public void nuevoOpcion(ActionEvent evento) {
        super.crearOpciones();
        this.opcion = new Opcion();
        this.opciones = new ArrayList<Opcion>();
        encerar();
    }
    
    public void agregarPreguntas(ActionEvent evento){
        
        if(pregunta != null){
            this.pregunta.setTextopregunta(textoPregunta);
            //this.pregunta.setIdtipopregunta());
            this.preguntas.add(pregunta);
            //
        } else {
            pregunta.setTextopregunta(null);
        }
        
    }

    public void agregarOpciones(ActionEvent evento) {

        try {
            if (opcion != null) {
                this.opcion.setDescripcion(opcionTexto);
                this.opcion.setValorOpcion(opcionValor);
                this.getOpcion().setIdopcion(BigDecimal.valueOf(getIdOpcion()));
                if (opciones == null) {
                    opciones = new ArrayList<Opcion>();
                }
                this.opciones.add(opcion);
            } else {
                opcion.setDescripcion(null);
                opcion.setValorOpcion(null);
            }
            encerar();
            this.setOpcion(new Opcion());
        } catch (Exception e) {
            //MensajesGenericos.sinOpciones();
        }

    }

    public void encerar() {
        if (opcionTexto != null) {
            opcionTexto = null;
            opcionValor = null;
        }

        setIdOpcion((Integer) 1);

        //setOpciones(new ArrayList<Opcion>());
    }

    /**
     * @return the tipoEvaluacion
     */
   

    /**
     * @return the tipoEvaluaciones
     */
    public List<TipoEvaluacion> getTipoEvaluaciones() {
        return tipoEvaluaciones;
    }

    /**
     * @param tipoEvaluaciones the tipoEvaluaciones to set
     */
    public void setTipoEvaluaciones(List<TipoEvaluacion> tipoEvaluaciones) {
        this.tipoEvaluaciones = tipoEvaluaciones;
    }

    /**
     * @return the tipoPregunta
     */
    public String getTipoPregunta() {
        return tipoPregunta;
    }

    /**
     * @param tipoPregunta the tipoPregunta to set
     */
    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /**
     * @return the tipoPreguntas
     */
    public List<TipoPregunta> getTipoPreguntas() {
        return tipoPreguntas;
    }

    /**
     * @param tipoPreguntas the tipoPreguntas to set
     */
    public void setTipoPreguntas(List<TipoPregunta> tipoPreguntas) {
        this.tipoPreguntas = tipoPreguntas;
    }

    /**
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
     * @return the textoPregunta
     */
    public String getTextoPregunta() {
        return textoPregunta;
    }

    /**
     * @param textoPregunta the textoPregunta to set
     */
    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
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

    /**
     * @return the preguntas
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * @param preguntas the preguntas to set
     */
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * @return the idTipoEvaluacion
     */
    public String getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    /**
     * @param idTipoEvaluacion the idTipoEvaluacion to set
     */
    public void setIdTipoEvaluacion(String idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    /**
     * @return the tipoEvaluacion
     */
    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    /**
     * @param tipoEvaluacion the tipoEvaluacion to set
     */
    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

}
