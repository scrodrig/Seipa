/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "PREGUNTAEVALUACION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntaevaluacion.findAll", query = "SELECT p FROM Preguntaevaluacion p"),
    @NamedQuery(name = "Preguntaevaluacion.findByIdpreevaluacion", query = "SELECT p FROM Preguntaevaluacion p WHERE p.idpreevaluacion = :idpreevaluacion"),
    @NamedQuery(name = "Preguntaevaluacion.findByEstadopreevaluacion", query = "SELECT p FROM Preguntaevaluacion p WHERE p.estadopreevaluacion = :estadopreevaluacion")})
public class Preguntaevaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPREEVALUACION", nullable = false, precision = 38, scale = 0)
    private BigDecimal idpreevaluacion;
    @Column(name = "ESTADOPREEVALUACION")
    private Character estadopreevaluacion;
    @JoinColumn(name = "IDPREGUNTA", referencedColumnName = "IDPREGUNTA")
    @ManyToOne
    private Pregunta idpregunta;
    @JoinColumn(name = "IDEVALUACION", referencedColumnName = "IDEVALUACION")
    @ManyToOne
    private Evaluacion idevaluacion;

    public Preguntaevaluacion() {
    }

    public Preguntaevaluacion(BigDecimal idpreevaluacion) {
        this.idpreevaluacion = idpreevaluacion;
    }

    public BigDecimal getIdpreevaluacion() {
        return idpreevaluacion;
    }

    public void setIdpreevaluacion(BigDecimal idpreevaluacion) {
        this.idpreevaluacion = idpreevaluacion;
    }

    public Character getEstadopreevaluacion() {
        return estadopreevaluacion;
    }

    public void setEstadopreevaluacion(Character estadopreevaluacion) {
        this.estadopreevaluacion = estadopreevaluacion;
    }

    public Pregunta getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Pregunta idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Evaluacion getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Evaluacion idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreevaluacion != null ? idpreevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntaevaluacion)) {
            return false;
        }
        Preguntaevaluacion other = (Preguntaevaluacion) object;
        if ((this.idpreevaluacion == null && other.idpreevaluacion != null) || (this.idpreevaluacion != null && !this.idpreevaluacion.equals(other.idpreevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Preguntaevaluacion[ idpreevaluacion=" + idpreevaluacion + " ]";
    }
    
}
