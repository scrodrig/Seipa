/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "PUNTAJE_DOCENTE", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntajeDocente.findAll", query = "SELECT p FROM PuntajeDocente p"),
    @NamedQuery(name = "PuntajeDocente.findById", query = "SELECT p FROM PuntajeDocente p WHERE p.id = :id"),
    @NamedQuery(name = "PuntajeDocente.findByPuntajeHeterogenera", query = "SELECT p FROM PuntajeDocente p WHERE p.puntajeHeterogenera = :puntajeHeterogenera"),
    @NamedQuery(name = "PuntajeDocente.findByPuntajeHomogenea", query = "SELECT p FROM PuntajeDocente p WHERE p.puntajeHomogenea = :puntajeHomogenea"),
    @NamedQuery(name = "PuntajeDocente.findByPuntajeCoevaluacion", query = "SELECT p FROM PuntajeDocente p WHERE p.puntajeCoevaluacion = :puntajeCoevaluacion")})
public class PuntajeDocente implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "PUNTAJE_HETEROGENERA", precision = 126)
    private Double puntajeHeterogenera;
    @Column(name = "PUNTAJE_HOMOGENEA", precision = 126)
    private Double puntajeHomogenea;
    @Column(name = "PUNTAJE_COEVALUACION", precision = 126)
    private Double puntajeCoevaluacion;
    @OneToMany(mappedBy = "idPuntaje")
    private List<Docente> docenteList;
    @JoinColumn(name = "ID_EVALUACION", referencedColumnName = "ID")
    @ManyToOne
    private Evaluacion idEvaluacion;

    public PuntajeDocente() {
    }

    public PuntajeDocente(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getPuntajeHeterogenera() {
        return puntajeHeterogenera;
    }

    public void setPuntajeHeterogenera(Double puntajeHeterogenera) {
        this.puntajeHeterogenera = puntajeHeterogenera;
    }

    public Double getPuntajeHomogenea() {
        return puntajeHomogenea;
    }

    public void setPuntajeHomogenea(Double puntajeHomogenea) {
        this.puntajeHomogenea = puntajeHomogenea;
    }

    public Double getPuntajeCoevaluacion() {
        return puntajeCoevaluacion;
    }

    public void setPuntajeCoevaluacion(Double puntajeCoevaluacion) {
        this.puntajeCoevaluacion = puntajeCoevaluacion;
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    public Evaluacion getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Evaluacion idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
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
        if (!(object instanceof PuntajeDocente)) {
            return false;
        }
        PuntajeDocente other = (PuntajeDocente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.PuntajeDocente[ id=" + id + " ]";
    }
    
}
