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
@Table(name = "TIPO_EVALUACION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEvaluacion.findAll", query = "SELECT t FROM TipoEvaluacion t"),
    @NamedQuery(name = "TipoEvaluacion.findByIdtipoevaluacion", query = "SELECT t FROM TipoEvaluacion t WHERE t.idtipoevaluacion = :idtipoevaluacion"),
    @NamedQuery(name = "TipoEvaluacion.findByDescripcion", query = "SELECT t FROM TipoEvaluacion t WHERE t.descripcion = :descripcion")})
public class TipoEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIPOEVALUACION", nullable = false, precision = 38, scale = 0)
    private BigDecimal idtipoevaluacion;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "idtipoevaluacion")
    private List<Evaluacion> evaluacionList;

    public TipoEvaluacion() {
    }

    public TipoEvaluacion(BigDecimal idtipoevaluacion) {
        this.idtipoevaluacion = idtipoevaluacion;
    }

    public BigDecimal getIdtipoevaluacion() {
        return idtipoevaluacion;
    }

    public void setIdtipoevaluacion(BigDecimal idtipoevaluacion) {
        this.idtipoevaluacion = idtipoevaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoevaluacion != null ? idtipoevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvaluacion)) {
            return false;
        }
        TipoEvaluacion other = (TipoEvaluacion) object;
        if ((this.idtipoevaluacion == null && other.idtipoevaluacion != null) || (this.idtipoevaluacion != null && !this.idtipoevaluacion.equals(other.idtipoevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.TipoEvaluacion[ idtipoevaluacion=" + idtipoevaluacion + " ]";
    }
    
}
