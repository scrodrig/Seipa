/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.Preguntaevaluacion;
import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.model.TipoPregunta;
import ec.edu.espe.seipa.service.EvaluacionServicio;
import ec.edu.espe.seipa.service.OpcionServicio;
import ec.edu.espe.seipa.service.PreguntaEvaluacionServicio;
import ec.edu.espe.seipa.service.PreguntaServicio;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import ec.edu.espe.seipa.service.TipoPreguntaServicio;
import ec.edu.espe.seipa.utils.EvaluacionUtils;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author SchubertDavid
 */
@ManagedBean
@ViewScoped
public class EvaluacionBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of EvaluacionBean
     */
    public EvaluacionBean() {
    }

    @EJB
    private EvaluacionServicio evaluacionServicio;

    @EJB
    private TipoEvaluacionServicio tipoEvaluacionServicio;

    @EJB
    private PreguntaServicio preguntaServicio;

    @EJB
    private OpcionServicio opcionServicio;

    @EJB
    private TipoPreguntaServicio tipoPreguntaServicio;

    @EJB
    private PreguntaEvaluacionServicio preguntaEvaluacionServicio;

    private List<Evaluacion> evaluaciones;
    private Evaluacion evaluacionSeleccionada;

    private List<Pregunta> preguntas;
    private Pregunta preguntaSelecciona;
    private Pregunta pregunta;
    private String tPregunta;

    private Evaluacion evaluacion;
    private String tEvaluacion;

    private List<TipoEvaluacion> tipoEvaluaciones;
    private TipoEvaluacion tipoEvaluacionN;

    private List<Opcion> opciones;
    private Opcion opcionSeleccionada;
    private Opcion opcion;

    private List<TipoPregunta> tipoPreguntas;
    private TipoPregunta tipoPreguntaCode;

    private Preguntaevaluacion preguntaEvaluacion;

    @PostConstruct
    public void postConstruct() {
        this.evaluaciones = this.evaluacionServicio.getEvaluaciones();
        this.tipoEvaluaciones = this.tipoEvaluacionServicio.obtener();
        this.tipoPreguntas = this.tipoPreguntaServicio.obtener();
        this.pregunta = new Pregunta();
        this.opcion = new Opcion();

    }

    public void seleccionarTipoEvaluacion(Evaluacion evaluacion) {
        this.evaluacionSeleccionada = evaluacion;
        this.preguntas = EvaluacionUtils.getPreguntaListFromEvaluacion(evaluacion);
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (getEvaluacionSeleccionada() instanceof Evaluacion) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.setEvaluacion(new Evaluacion());
    }

    public void nuevaPregunta(ActionEvent evento) {
        super.crearPreguntas();
        this.setEvaluacion(new Evaluacion());
        try {
            this.evaluacion = (Evaluacion) BeanUtils.cloneBean(this.evaluacionSeleccionada);
            System.out.println(this.evaluacionSeleccionada.getIdevaluacion().toString());
            this.preguntas = preguntaServicio.findByEvaluacion(evaluacionSeleccionada);
            //super.verDetalles();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void nuevaP(ActionEvent evento) {
        super.crearPreguntas();
        this.setPregunta(new Pregunta());
    }

    public void nuevaOpcion(ActionEvent evento) {
        super.crearPreguntas();
        this.setPregunta(new Pregunta());
        try {
            this.pregunta = (Pregunta) BeanUtils.cloneBean(this.preguntaSelecciona);
            System.out.println(this.preguntaSelecciona.getIdpregunta().toString());
            this.setOpciones(opcionServicio.findByPregunta(preguntaSelecciona));
            //super.verDetalles();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        setEvaluacion(new Evaluacion());
        MensajesGenericos.infoCancelar();
    }

    public void guardar(ActionEvent evento) {
        try {
            this.setTipoEvaluacionN(new TipoEvaluacion());
            this.tipoEvaluacionN.setIdtipoevaluacion(new BigDecimal(gettEvaluacion().substring(57, 59).trim()));
            if (this.evaluacion.getIdtipoevaluacion() == null) {
                this.evaluacion.setIdevaluacion(new BigDecimal(evaluacionServicio.codigoNuevoEvaluacion()).add(new BigDecimal("1")));
            }
            java.util.Date fecha = new Date();
            this.evaluacion.setFechaCreacion(fecha);
            this.evaluacion.setIdtipoevaluacion(tipoEvaluacionN);
            if (super.getEnRegistro()) {
                this.evaluacionServicio.crear(this.evaluacion);
                this.evaluaciones.add(this.evaluacion);
                MensajesGenericos.infoCrear("Evaluacion", this.evaluacion.getDescripcion().concat(" - Creada con exito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.evaluaciones.indexOf(this.evaluacion);
                this.evaluacionServicio.actualizar(this.evaluacion);
                evaluaciones.set(i, this.evaluacion);
                MensajesGenericos.infoModificar("Evaluacion", this.evaluacion.getDescripcion().concat(" - Modificada con exito "), Boolean.TRUE);
                super.sinSeleccion();
            }

        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarPregunta(ActionEvent evento) {
        try {
            this.setTipoPreguntaCode(new TipoPregunta());
            System.out.println(tPregunta);
            this.tipoPreguntaCode.setTipoPregunta(gettPregunta().substring(52, 50).trim());
            if (this.pregunta.getIdpregunta() == null) {
                this.pregunta.setIdpregunta(new BigDecimal(preguntaServicio.codigoNuevoPregunta()).add(new BigDecimal("1")));
            }
            if (super.getEnRegitroPregunta()) {
                //Envio de datos a pregunta Evaluacion
                if (this.preguntaEvaluacion.getIdpreevaluacion() == null) {
                    this.preguntaEvaluacion.setIdpreevaluacion(new BigDecimal(preguntaEvaluacionServicio.codigoNuevoPreguntaEvaluacion()).add(new BigDecimal("1")));
                }
                char a = 1;
                this.preguntaEvaluacion.setIdpregunta(pregunta);
                this.preguntaEvaluacion.setIdevaluacion(evaluacion);
                this.preguntaEvaluacion.setEstadopreevaluacion(a);
                this.preguntaServicio.crear(this.pregunta);
                this.preguntaEvaluacionServicio.crear(preguntaEvaluacion);
                this.preguntas.add(this.pregunta);
                MensajesGenericos.infoCrear("Pregunta", this.pregunta.getIdpregunta().toString().concat(" - Creada con exito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicionPregunta()) {
                int i = this.preguntas.indexOf(this.pregunta);
                this.preguntaServicio.actualizar(this.pregunta);
                evaluaciones.set(i, this.evaluacion);
                MensajesGenericos.infoModificar("Pregunta", this.pregunta.getIdpregunta().toString().concat(" - Modificada con exito "), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }
    }

    public void modificar(ActionEvent evento) {
        this.setEvaluacion(new Evaluacion());
        try {
            this.evaluacion = (Evaluacion) BeanUtils.cloneBean(this.evaluacionSeleccionada);
            this.evaluacionServicio.actualizar(this.evaluacion);
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Evaluacion getEvaluacionSeleccionada() {
        return evaluacionSeleccionada;
    }

    public void setEvaluacionSeleccionada(Evaluacion evaluacionSeleccionada) {
        this.evaluacionSeleccionada = evaluacionSeleccionada;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    /**
     * @return the evaluacion
     */
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the evaluacion to set
     */
    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * @return the preguntaSelecciona
     */
    public Pregunta getPreguntaSelecciona() {
        return preguntaSelecciona;
    }

    /**
     * @param preguntaSelecciona the preguntaSelecciona to set
     */
    public void setPreguntaSelecciona(Pregunta preguntaSelecciona) {
        this.preguntaSelecciona = preguntaSelecciona;
    }

    /**
     * @return the tipoEvaluacionN
     */
    public TipoEvaluacion getTipoEvaluacionN() {
        return tipoEvaluacionN;
    }

    /**
     * @param tipoEvaluacionN the tipoEvaluacionN to set
     */
    public void setTipoEvaluacionN(TipoEvaluacion tipoEvaluacionN) {
        this.tipoEvaluacionN = tipoEvaluacionN;
    }

    /**
     * @return the tEvaluacion
     */
    public String gettEvaluacion() {
        return tEvaluacion;
    }

    /**
     * @param tEvaluacion the tEvaluacion to set
     */
    public void settEvaluacion(String tEvaluacion) {
        this.tEvaluacion = tEvaluacion;
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
     * @return the opcionSeleccionada
     */
    public Opcion getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    /**
     * @param opcionSeleccionada the opcionSeleccionada to set
     */
    public void setOpcionSeleccionada(Opcion opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }

    /**
     * @return the tPregunta
     */
    public String gettPregunta() {
        return tPregunta;
    }

    /**
     * @param tPregunta the tPregunta to set
     */
    public void settPregunta(String tPregunta) {
        this.tPregunta = tPregunta;
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
     * @return the tipoPreguntaCode
     */
    public TipoPregunta getTipoPreguntaCode() {
        return tipoPreguntaCode;
    }

    /**
     * @param tipoPreguntaCode the tipoPreguntaCode to set
     */
    public void setTipoPreguntaCode(TipoPregunta tipoPreguntaCode) {
        this.tipoPreguntaCode = tipoPreguntaCode;
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
     * @return the preguntaEvaluacion
     */
    public Preguntaevaluacion getPreguntaEvaluacion() {
        return preguntaEvaluacion;
    }

    /**
     * @param preguntaEvaluacion the preguntaEvaluacion to set
     */
    public void setPreguntaEvaluacion(Preguntaevaluacion preguntaEvaluacion) {
        this.preguntaEvaluacion = preguntaEvaluacion;
    }

}
