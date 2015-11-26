/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.Preguntaevaluacion;
import ec.edu.espe.seipa.model.Preguntaopcion;
import ec.edu.espe.seipa.model.Sumario;
import ec.edu.espe.seipa.model.Sumarioopcion;
import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.model.TipoPregunta;
import ec.edu.espe.seipa.service.EvaluacionServicio;
import ec.edu.espe.seipa.service.OpcionServicio;
import ec.edu.espe.seipa.service.PreguntaEvaluacionServicio;
import ec.edu.espe.seipa.service.PreguntaOpcionServicio;
import ec.edu.espe.seipa.service.PreguntaServicio;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import ec.edu.espe.seipa.service.TipoPreguntaServicio;
import ec.edu.espe.seipa.utils.EvaluacionUtils;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import ec.edu.espe.seipa.utils.PreguntaRespuestaCls;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;


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

    @EJB
    private PreguntaOpcionServicio preguntaOpcionServicio;

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
    private String orderNumberPregunta;
    private Opcion opcion;
    private String idOpcion;
    private String idPreguntaOpcion;

    private List<TipoPregunta> tipoPreguntas;
    private TipoPregunta tipoPreguntaCode;

    private Preguntaevaluacion preguntaEvaluacion;

    private Preguntaopcion preguntaOpcion;
    private Docente docente;
    
    @PostConstruct
    public void postConstruct() {
        this.evaluaciones = this.evaluacionServicio.getEvaluaciones();
        this.tipoEvaluaciones = this.tipoEvaluacionServicio.obtener();
        this.tipoPreguntas = this.tipoPreguntaServicio.obtener();
        this.pregunta = new Pregunta();
        this.opcion = new Opcion();
        this.docente=((Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente"));
    }

    public void seleccionarTipoEvaluacion(Evaluacion evaluacion) {
        this.evaluacionSeleccionada = evaluacion;
        this.preguntas = EvaluacionUtils.getPreguntaListFromEvaluacion(evaluacion);
        super.verBotonTerminar();
        this.preguntaResp.clear();
        for(Pregunta pre:this.preguntas)
        {
            this.preguntaResp.add(new PreguntaRespuestaCls(pre));
        }

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

    public void nuevoOpcion(ActionEvent evento) {
        super.crearOpciones();
        this.opcion = new Opcion();
    }

    public void nuevaPregunta(ActionEvent evento) {
        super.crearPreguntas();
        this.setEvaluacion(new Evaluacion());
        try {
            this.evaluacion = (Evaluacion) BeanUtils.cloneBean(this.evaluacionSeleccionada);
            System.out.println(this.evaluacion.getIdevaluacion().toString());
            this.preguntas = preguntaServicio.findByEvaluacion(evaluacion);
            //super.verDetalles();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void nuevaP(ActionEvent evento) {
        super.crearPreguntas();
        this.pregunta = new Pregunta();
    }

    public void nuevaOpcion(ActionEvent evento) {
        super.crearPreguntas();
        this.setPregunta(new Pregunta());
        try {
            this.pregunta = (Pregunta) BeanUtils.cloneBean(this.preguntaSelecciona);
            System.out.println(this.pregunta.getIdpregunta().toString());
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
            this.tipoEvaluacionN = new TipoEvaluacion();
            this.tipoEvaluacionN.setIdtipoevaluacion(new BigDecimal(tEvaluacion.substring(57, tEvaluacion.length() - 1).trim()));
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
            this.tipoPreguntaCode = new TipoPregunta();
            this.tipoPreguntaCode.setIdtipopregunta(new BigDecimal(tPregunta.substring(53, tPregunta.length() - 1).trim()));
            this.pregunta.setIdtipopregunta(tipoPreguntaCode);
            System.out.println(tPregunta);
            if (this.pregunta.getIdpregunta() == null) {
                this.pregunta.setIdpregunta(new BigDecimal(preguntaServicio.codigoNuevoPregunta()).add(new BigDecimal("1")));
            }
            if (super.getEnRegitroPregunta()) {
                //Envio de datos a pregunta Evaluacion
                if (this.preguntaEvaluacion == null) {
                    this.preguntaEvaluacion = new Preguntaevaluacion();
                    this.preguntaEvaluacion.setIdpreevaluacion(new BigDecimal(preguntaEvaluacionServicio.codigoNuevoPreguntaEvaluacion()).add(new BigDecimal("1")));
                }
                char a = '1';
                this.preguntaEvaluacion.setIdpregunta(pregunta);
                this.preguntaEvaluacion.setIdevaluacion(evaluacion);
                this.preguntaEvaluacion.setEstadopreevaluacion(a);

                this.setOrderNumberPregunta(preguntaServicio.findOrderNumberPregunta(evaluacion.getIdevaluacion().toString()));
                //Numero de orden de las preguntas 

                    this.pregunta.setOrdernumber(new BigInteger(getOrderNumberPregunta()).add(new BigInteger("1")));
                    //this.pregunta.setOrdernumber(new BigInteger(orderNumberPregunta).add(new BigInteger("1")));
                //Guardar Pregunta.
                this.preguntaServicio.crear(this.pregunta);
                //Guarda Pregunta Correspondiente a Evaluacion.
                this.preguntaEvaluacionServicio.crear(preguntaEvaluacion);
                //Agrega pregunta a listado de preguntas de la Evaluacion.
                this.preguntas.add(this.pregunta);
                //Mensaje de Exito al guardar.
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

    public void guardarOpcion(ActionEvent evento) {

        try {

            //Claves Primarias Opcion & OpcionPregunta
            this.idOpcion = opcionServicio.opcionId();
            this.idPreguntaOpcion = opcionServicio.opcionPreguntaId();

            if (super.getEnRegistroOpciones()) {

                //Registro Opciones
                this.opcion.setIdopcion(new BigDecimal(idOpcion).add(new BigDecimal("1")));
                this.opcionServicio.crear(opcion);
                
                //Registro Opciones Pregunta
                char a = '1';
                this.preguntaOpcion.setIdopcion(opcion);
                this.preguntaOpcion.setIdpreopcion(new BigDecimal(idPreguntaOpcion).add(new BigDecimal("1")));
                this.preguntaOpcion.setIdpregunta(pregunta);
                this.preguntaOpcion.setEstadopreopcion(a);
                this.preguntaOpcionServicio.crear(preguntaOpcion);
                
                //Agrego opcion a una lista
                this.opciones.add(opcion);

            } else if (super.getEnEdicionOpciones()) {

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

    /**
     * @return the idOpcion
     */
    public String getIdOpcion() {
        return idOpcion;
    }

    /**
     * @param idOpcion the idOpcion to set
     */
    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    /**
     * @return the idPreguntaOpcion
     */
    public String getIdPreguntaOpcion() {
        return idPreguntaOpcion;
    }

    /**
     * @param idPreguntaOpcion the idPreguntaOpcion to set
     */
    public void setIdPreguntaOpcion(String idPreguntaOpcion) {
        this.idPreguntaOpcion = idPreguntaOpcion;
    }

    /**
     * @return the preguntaOpcion
     */
    public Preguntaopcion getPreguntaOpcion() {
        return preguntaOpcion;
    }

    /**
     * @param preguntaOpcion the preguntaOpcion to set
     */
    public void setPreguntaOpcion(Preguntaopcion preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }

    private List<PreguntaRespuestaCls> preguntaResp = new ArrayList();

    public List<PreguntaRespuestaCls> getPreguntaResp() {
        return preguntaResp;
    }

    public void setPreguntaResp(List<PreguntaRespuestaCls> preguntaResp) {
        this.preguntaResp = preguntaResp;
    }

    public void guardaEvaluacion()
    {
        Sumarioopcion objSumarioopcion;
        Sumario objSumario;
        try
        {
            objSumario=new Sumario();
            
            objSumario.setIdevaluacion((Evaluacion) BeanUtils.cloneBean(this.evaluacionSeleccionada));
            objSumario.setEstadoevaluacion("1");
            objSumario.setIdsumario(new BigDecimal(this.evaluacionServicio.codigoNuevoSumario()).add(new BigDecimal("1")));
            objSumario.setId(docente);
            objSumario.setPorcentajeObtenido(Double.NaN);
            objSumario.setPuntajeObtenido(Double.NaN);
            
            this.evaluacionServicio.crearSumario(objSumario);
            //Recorrer por todas las preguntas
            for(PreguntaRespuestaCls objPreguntaRespuestaCls:preguntaResp)
            {
                objSumarioopcion=new Sumarioopcion();
                try
                {
                    objSumarioopcion.setIdopcion(this.evaluacionServicio.getOpcionFind(objPreguntaRespuestaCls.getRespuesta()));
                }
                catch(Exception ex)
                {
                    //Es pregunta abierta u otra
                    objSumarioopcion.setIdopcion(objPreguntaRespuestaCls.getPregunta().getOpcionList().get(0));
                }
                
                objSumarioopcion.setIdsumario(objSumario);
                objSumarioopcion.setIdsumarionopcion(new BigDecimal(this.evaluacionServicio.codigoNuevoSumarioopcion()).add(new BigDecimal("1")));
                objSumarioopcion.setValorobtenido(objPreguntaRespuestaCls.getRespuesta());
                this.evaluacionServicio.crearSumarioopcion(objSumarioopcion);
                
                

            }
            
            objSumario.setPorcentajeObtenido(Double.NaN);
            objSumario.setPuntajeObtenido(Double.NaN);
            
            this.evaluacionServicio.actualizarSumario(objSumario);

            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluación", "Se guardó correctamente."));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Evaluación", ex.getMessage()));
        }
    }

    /**
     * @return the orderNumberPregunta
     */
    public String getOrderNumberPregunta() {
        return orderNumberPregunta;
    }

    /**
     * @param orderNumberPregunta the orderNumberPregunta to set
     */
    public void setOrderNumberPregunta(String orderNumberPregunta) {
        this.orderNumberPregunta = orderNumberPregunta;
    }

}
