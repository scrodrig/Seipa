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
@Table(name = "HORASDOCENTE", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horasdocente.findAll", query = "SELECT h FROM Horasdocente h"),
    @NamedQuery(name = "Horasdocente.findByIdhorasdocente", query = "SELECT h FROM Horasdocente h WHERE h.idhorasdocente = :idhorasdocente"),
    @NamedQuery(name = "Horasdocente.findByHoras", query = "SELECT h FROM Horasdocente h WHERE h.horas = :horas"),
    @NamedQuery(name = "Horasdocente.findByTipohoras", query = "SELECT h FROM Horasdocente h WHERE h.tipohoras = :tipohoras")})
public class Horasdocente implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDHORASDOCENTE", nullable = false, precision = 38, scale = 0)
    private BigDecimal idhorasdocente;
    @Column(name = "HORAS", precision = 126)
    private Double horas;
    @Size(max = 150)
    @Column(name = "TIPOHORAS", length = 150)
    private String tipohoras;
    @JoinColumn(name = "IDDOCENTE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Docente iddocente;

    public Horasdocente() {
    }

    public Horasdocente(BigDecimal idhorasdocente) {
        this.idhorasdocente = idhorasdocente;
    }

    public BigDecimal getIdhorasdocente() {
        return idhorasdocente;
    }

    public void setIdhorasdocente(BigDecimal idhorasdocente) {
        this.idhorasdocente = idhorasdocente;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public String getTipohoras() {
        return tipohoras;
    }

    public void setTipohoras(String tipohoras) {
        this.tipohoras = tipohoras;
    }

    public Docente getIddocente() {
        return iddocente;
    }

    public void setIddocente(Docente iddocente) {
        this.iddocente = iddocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorasdocente != null ? idhorasdocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horasdocente)) {
            return false;
        }
        Horasdocente other = (Horasdocente) object;
        if ((this.idhorasdocente == null && other.idhorasdocente != null) || (this.idhorasdocente != null && !this.idhorasdocente.equals(other.idhorasdocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Horasdocente[ idhorasdocente=" + idhorasdocente + " ]";
    }
    
}
