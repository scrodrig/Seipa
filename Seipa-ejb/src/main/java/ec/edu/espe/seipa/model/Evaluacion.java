/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SchubertDavid
 */
@Entity
@Table(name = "EVALUACION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e")})
public class Evaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Size(max = 600)
    @Column(name = "RESPUESTA", length = 600)
    private String respuesta;
    @Column(name = "PUNTAJE", precision = 126)
    private Double puntaje;
    @Column(name = "FECHAEVALUACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaevaluacion;
    @Column(name = "NUM_EVALUACION")
    private BigInteger numEvaluacion;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID")
    @ManyToOne
    private Preguntas idPregunta;
    @OneToMany(mappedBy = "idEvaluacion")
    private List<PuntajeDocente> puntajeDocenteList;
    @OneToMany(mappedBy = "idEvaluacionDocente")
    private List<ResultadosHeterogeneas> resultadosHeterogeneasList;
    @OneToMany(mappedBy = "idEvaluacion")
    private List<Parametros> parametrosList;

    public Evaluacion() {
    }

    public Evaluacion(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Date getFechaevaluacion() {
        return fechaevaluacion;
    }

    public void setFechaevaluacion(Date fechaevaluacion) {
        this.fechaevaluacion = fechaevaluacion;
    }

    public BigInteger getNumEvaluacion() {
        return numEvaluacion;
    }

    public void setNumEvaluacion(BigInteger numEvaluacion) {
        this.numEvaluacion = numEvaluacion;
    }

    public Preguntas getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Preguntas idPregunta) {
        this.idPregunta = idPregunta;
    }

    @XmlTransient
    public List<PuntajeDocente> getPuntajeDocenteList() {
        return puntajeDocenteList;
    }

    public void setPuntajeDocenteList(List<PuntajeDocente> puntajeDocenteList) {
        this.puntajeDocenteList = puntajeDocenteList;
    }

    @XmlTransient
    public List<ResultadosHeterogeneas> getResultadosHeterogeneasList() {
        return resultadosHeterogeneasList;
    }

    public void setResultadosHeterogeneasList(List<ResultadosHeterogeneas> resultadosHeterogeneasList) {
        this.resultadosHeterogeneasList = resultadosHeterogeneasList;
    }

    @XmlTransient
    public List<Parametros> getParametrosList() {
        return parametrosList;
    }

    public void setParametrosList(List<Parametros> parametrosList) {
        this.parametrosList = parametrosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Evaluacion[ id=" + id + " ]";
    }
    
}
