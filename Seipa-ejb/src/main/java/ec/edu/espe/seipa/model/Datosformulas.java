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
@Table(name = "DATOSFORMULAS", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datosformulas.findAll", query = "SELECT d FROM Datosformulas d"),
    @NamedQuery(name = "Datosformulas.findByIddatosformula", query = "SELECT d FROM Datosformulas d WHERE d.iddatosformula = :iddatosformula"),
    @NamedQuery(name = "Datosformulas.findByParametro", query = "SELECT d FROM Datosformulas d WHERE d.parametro = :parametro"),
    @NamedQuery(name = "Datosformulas.findByValorparametro", query = "SELECT d FROM Datosformulas d WHERE d.valorparametro = :valorparametro"),
    @NamedQuery(name = "Datosformulas.findByEstadoparametro", query = "SELECT d FROM Datosformulas d WHERE d.estadoparametro = :estadoparametro")})
public class Datosformulas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDATOSFORMULA", nullable = false, precision = 38, scale = 0)
    private BigDecimal iddatosformula;
    @Size(max = 100)
    @Column(name = "PARAMETRO", length = 100)
    private String parametro;
    @Column(name = "VALORPARAMETRO", precision = 126)
    private Double valorparametro;
    @Column(name = "ESTADOPARAMETRO")
    private Character estadoparametro;

    public Datosformulas() {
    }

    public Datosformulas(BigDecimal iddatosformula) {
        this.iddatosformula = iddatosformula;
    }

    public BigDecimal getIddatosformula() {
        return iddatosformula;
    }

    public void setIddatosformula(BigDecimal iddatosformula) {
        this.iddatosformula = iddatosformula;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Double getValorparametro() {
        return valorparametro;
    }

    public void setValorparametro(Double valorparametro) {
        this.valorparametro = valorparametro;
    }

    public Character getEstadoparametro() {
        return estadoparametro;
    }

    public void setEstadoparametro(Character estadoparametro) {
        this.estadoparametro = estadoparametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddatosformula != null ? iddatosformula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosformulas)) {
            return false;
        }
        Datosformulas other = (Datosformulas) object;
        if ((this.iddatosformula == null && other.iddatosformula != null) || (this.iddatosformula != null && !this.iddatosformula.equals(other.iddatosformula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Datosformulas[ iddatosformula=" + iddatosformula + " ]";
    }
    
}
