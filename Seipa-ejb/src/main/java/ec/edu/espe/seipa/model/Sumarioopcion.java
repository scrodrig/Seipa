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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "SUMARIOOPCION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sumarioopcion.findAll", query = "SELECT s FROM Sumarioopcion s"),
    @NamedQuery(name = "Sumarioopcion.findByValorobtenido", query = "SELECT s FROM Sumarioopcion s WHERE s.valorobtenido = :valorobtenido"),
    @NamedQuery(name = "Sumarioopcion.findByIdsumarionopcion", query = "SELECT s FROM Sumarioopcion s WHERE s.idsumarionopcion = :idsumarionopcion")})
public class Sumarioopcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 250)
    @Column(name = "VALOROBTENIDO", length = 250)
    private String valorobtenido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSUMARIONOPCION", nullable = false, precision = 0, scale = -127)
    private BigDecimal idsumarionopcion;
    @JoinColumn(name = "IDSUMARIO", referencedColumnName = "IDSUMARIO")
    @ManyToOne
    private Sumario idsumario;
    @JoinColumn(name = "IDOPCION", referencedColumnName = "IDOPCION")
    @ManyToOne
    private Opcion idopcion;

    public Sumarioopcion() {
    }

    public Sumarioopcion(BigDecimal idsumarionopcion) {
        this.idsumarionopcion = idsumarionopcion;
    }

    public String getValorobtenido() {
        return valorobtenido;
    }

    public void setValorobtenido(String valorobtenido) {
        this.valorobtenido = valorobtenido;
    }

    public BigDecimal getIdsumarionopcion() {
        return idsumarionopcion;
    }

    public void setIdsumarionopcion(BigDecimal idsumarionopcion) {
        this.idsumarionopcion = idsumarionopcion;
    }

    public Sumario getIdsumario() {
        return idsumario;
    }

    public void setIdsumario(Sumario idsumario) {
        this.idsumario = idsumario;
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
        hash += (idsumarionopcion != null ? idsumarionopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sumarioopcion)) {
            return false;
        }
        Sumarioopcion other = (Sumarioopcion) object;
        if ((this.idsumarionopcion == null && other.idsumarionopcion != null) || (this.idsumarionopcion != null && !this.idsumarionopcion.equals(other.idsumarionopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Sumarioopcion[ idsumarionopcion=" + idsumarionopcion + " ]";
    }
    
}
