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
@Table(name = "PREGUNTAOPCION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntaopcion.findAll", query = "SELECT p FROM Preguntaopcion p"),
    @NamedQuery(name = "Preguntaopcion.findByIdpreopcion", query = "SELECT p FROM Preguntaopcion p WHERE p.idpreopcion = :idpreopcion"),
    @NamedQuery(name = "Preguntaopcion.findByEstadopreopcion", query = "SELECT p FROM Preguntaopcion p WHERE p.estadopreopcion = :estadopreopcion")})
public class Preguntaopcion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPREOPCION", nullable = false, precision = 38, scale = 0)
    private BigDecimal idpreopcion;
    @Column(name = "ESTADOPREOPCION")
    private Character estadopreopcion;
    @JoinColumn(name = "IDPREGUNTA", referencedColumnName = "IDPREGUNTA")
    @ManyToOne
    private Pregunta idpregunta;
    @JoinColumn(name = "IDOPCION", referencedColumnName = "IDOPCION")
    @ManyToOne
    private Opcion idopcion;

    public Preguntaopcion() {
    }

    public Preguntaopcion(BigDecimal idpreopcion) {
        this.idpreopcion = idpreopcion;
    }

    public BigDecimal getIdpreopcion() {
        return idpreopcion;
    }

    public void setIdpreopcion(BigDecimal idpreopcion) {
        this.idpreopcion = idpreopcion;
    }

    public Character getEstadopreopcion() {
        return estadopreopcion;
    }

    public void setEstadopreopcion(Character estadopreopcion) {
        this.estadopreopcion = estadopreopcion;
    }

    public Pregunta getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Pregunta idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Opcion getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Opcion idopcion) {
        this.idopcion = idopcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreopcion != null ? idpreopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntaopcion)) {
            return false;
        }
        Preguntaopcion other = (Preguntaopcion) object;
        if ((this.idpreopcion == null && other.idpreopcion != null) || (this.idpreopcion != null && !this.idpreopcion.equals(other.idpreopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Preguntaopcion[ idpreopcion=" + idpreopcion + " ]";
    }
    
}
