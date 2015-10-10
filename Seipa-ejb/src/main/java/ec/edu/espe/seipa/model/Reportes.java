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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "REPORTES", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r"),
    @NamedQuery(name = "Reportes.findById", query = "SELECT r FROM Reportes r WHERE r.id = :id"),
    @NamedQuery(name = "Reportes.findByTitulo", query = "SELECT r FROM Reportes r WHERE r.titulo = :titulo"),
    @NamedQuery(name = "Reportes.findByDescripcion", query = "SELECT r FROM Reportes r WHERE r.descripcion = :descripcion")})
public class Reportes implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Size(max = 300)
    @Column(name = "TITULO", length = 300)
    private String titulo;
    @Size(max = 300)
    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
    @OneToMany(mappedBy = "idReporte")
    private List<DetalleReporte> detalleReporteList;

    public Reportes() {
    }

    public Reportes(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DetalleReporte> getDetalleReporteList() {
        return detalleReporteList;
    }

    public void setDetalleReporteList(List<DetalleReporte> detalleReporteList) {
        this.detalleReporteList = detalleReporteList;
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
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Reportes[ id=" + id + " ]";
    }
    
}
