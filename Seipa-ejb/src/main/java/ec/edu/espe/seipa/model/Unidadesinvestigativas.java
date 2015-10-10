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
@Table(name = "UNIDADESINVESTIGATIVAS", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadesinvestigativas.findAll", query = "SELECT u FROM Unidadesinvestigativas u"),
    @NamedQuery(name = "Unidadesinvestigativas.findById", query = "SELECT u FROM Unidadesinvestigativas u WHERE u.id = :id"),
    @NamedQuery(name = "Unidadesinvestigativas.findByNombreProyecto", query = "SELECT u FROM Unidadesinvestigativas u WHERE u.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "Unidadesinvestigativas.findByDescripcion", query = "SELECT u FROM Unidadesinvestigativas u WHERE u.descripcion = :descripcion")})
public class Unidadesinvestigativas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "NOMBRE_PROYECTO", length = 100)
    private String nombreProyecto;
    @Size(max = 500)
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID")
    @ManyToOne
    private Departamento idDepartamento;

    public Unidadesinvestigativas() {
    }

    public Unidadesinvestigativas(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        if (!(object instanceof Unidadesinvestigativas)) {
            return false;
        }
        Unidadesinvestigativas other = (Unidadesinvestigativas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Unidadesinvestigativas[ id=" + id + " ]";
    }
    
}
