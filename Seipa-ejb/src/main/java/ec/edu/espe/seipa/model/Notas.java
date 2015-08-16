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
 * @author SchubertDavid
 */
@Entity
@Table(name = "NOTAS", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n")})
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "PUNTAJEP1", precision = 126)
    private Double puntajep1;
    @Column(name = "PUNTAJEP2", precision = 126)
    private Double puntajep2;
    @Column(name = "PUNTAJEP3", precision = 126)
    private Double puntajep3;
    @Column(name = "TOTAL", precision = 126)
    private Double total;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID")
    @ManyToOne
    private Materia idMateria;

    public Notas() {
    }

    public Notas(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getPuntajep1() {
        return puntajep1;
    }

    public void setPuntajep1(Double puntajep1) {
        this.puntajep1 = puntajep1;
    }

    public Double getPuntajep2() {
        return puntajep2;
    }

    public void setPuntajep2(Double puntajep2) {
        this.puntajep2 = puntajep2;
    }

    public Double getPuntajep3() {
        return puntajep3;
    }

    public void setPuntajep3(Double puntajep3) {
        this.puntajep3 = puntajep3;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
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
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Notas[ id=" + id + " ]";
    }
    
}
