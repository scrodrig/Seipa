/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author ronny
 */
@Entity
@Table(name = "EVALUACION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdevaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idevaluacion = :idevaluacion"),
    @NamedQuery(name = "Evaluacion.findByDescripcion", query = "SELECT e FROM Evaluacion e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Evaluacion.findByFechaCreacion", query = "SELECT e FROM Evaluacion e WHERE e.fechaCreacion = :fechaCreacion")})
public class Evaluacion implements Serializable {
    @Size(max = 100)
    @Column(name = "ESTADO", length = 100)
    private String estado;
    @Column(name = "FEC_LIMITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecLimite;
    @OneToMany(mappedBy = "idevaluacion")
    private List<Preguntaevaluacion> preguntaevaluacionList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEVALUACION", nullable = false, precision = 38, scale = 0)
    private BigDecimal idevaluacion;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinTable(name = "PREGUNTAEVALUACION", joinColumns = {
        @JoinColumn(name = "IDEVALUACION", referencedColumnName = "IDEVALUACION")}, inverseJoinColumns = {
        @JoinColumn(name = "IDPREGUNTA", referencedColumnName = "IDPREGUNTA")})
    @ManyToMany
    private List<Pregunta> preguntaList;
    @JoinColumn(name = "IDTIPOEVALUACION", referencedColumnName = "IDTIPOEVALUACION")
    @ManyToOne
    private TipoEvaluacion idtipoevaluacion;
    @OneToMany(mappedBy = "idevaluacion")
    private List<Sumario> sumarioList;

    public Evaluacion() {
    }

    public Evaluacion(BigDecimal idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public BigDecimal getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(BigDecimal idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public TipoEvaluacion getIdtipoevaluacion() {
        return idtipoevaluacion;
    }

    public void setIdtipoevaluacion(TipoEvaluacion idtipoevaluacion) {
        this.idtipoevaluacion = idtipoevaluacion;
    }

    @XmlTransient
    public List<Sumario> getSumarioList() {
        return sumarioList;
    }

    public void setSumarioList(List<Sumario> sumarioList) {
        this.sumarioList = sumarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluacion != null ? idevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idevaluacion == null && other.idevaluacion != null) || (this.idevaluacion != null && !this.idevaluacion.equals(other.idevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Evaluacion[ idevaluacion=" + idevaluacion + " ]";
    }

    @XmlTransient
    public List<Preguntaevaluacion> getPreguntaevaluacionList() {
        return preguntaevaluacionList;
    }

    public void setPreguntaevaluacionList(List<Preguntaevaluacion> preguntaevaluacionList) {
        this.preguntaevaluacionList = preguntaevaluacionList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecLimite() {
        return fecLimite;
    }

    public void setFecLimite(Date fecLimite) {
        this.fecLimite = fecLimite;
    }
    
}
