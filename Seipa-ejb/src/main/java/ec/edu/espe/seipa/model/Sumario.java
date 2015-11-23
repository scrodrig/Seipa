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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "SUMARIO", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sumario.findAll", query = "SELECT s FROM Sumario s"),
    @NamedQuery(name = "Sumario.findByIdsumario", query = "SELECT s FROM Sumario s WHERE s.idsumario = :idsumario"),
    @NamedQuery(name = "Sumario.findByPuntajeObtenido", query = "SELECT s FROM Sumario s WHERE s.puntajeObtenido = :puntajeObtenido"),
    @NamedQuery(name = "Sumario.findByPorcentajeObtenido", query = "SELECT s FROM Sumario s WHERE s.porcentajeObtenido = :porcentajeObtenido")})
public class Sumario implements Serializable {
    @OneToMany(mappedBy = "idsumario")
    private List<Sumarioopcion> sumarioopcionList;
    @Size(max = 100)
    @Column(name = "ESTADOEVALUACION", length = 100)
    private String estadoevaluacion;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSUMARIO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idsumario;
    @Column(name = "PUNTAJE_OBTENIDO", precision = 126)
    private Double puntajeObtenido;
    @Column(name = "PORCENTAJE_OBTENIDO", precision = 126)
    private Double porcentajeObtenido;
    @JoinColumn(name = "IDEVALUACION", referencedColumnName = "IDEVALUACION")
    @ManyToOne
    private Evaluacion idevaluacion;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private Docente id;

    public Sumario() {
    }

    public Sumario(BigDecimal idsumario) {
        this.idsumario = idsumario;
    }

    public BigDecimal getIdsumario() {
        return idsumario;
    }

    public void setIdsumario(BigDecimal idsumario) {
        this.idsumario = idsumario;
    }

    public Double getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(Double puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public Double getPorcentajeObtenido() {
        return porcentajeObtenido;
    }

    public void setPorcentajeObtenido(Double porcentajeObtenido) {
        this.porcentajeObtenido = porcentajeObtenido;
    }

    public Evaluacion getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Evaluacion idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public Docente getId() {
        return id;
    }

    public void setId(Docente id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsumario != null ? idsumario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sumario)) {
            return false;
        }
        Sumario other = (Sumario) object;
        if ((this.idsumario == null && other.idsumario != null) || (this.idsumario != null && !this.idsumario.equals(other.idsumario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Sumario[ idsumario=" + idsumario + " ]";
    }

    public String getEstadoevaluacion() {
        return estadoevaluacion;
    }

    public void setEstadoevaluacion(String estadoevaluacion) {
        this.estadoevaluacion = estadoevaluacion;
    }

    @XmlTransient
    public List<Sumarioopcion> getSumarioopcionList() {
        return sumarioopcionList;
    }

    public void setSumarioopcionList(List<Sumarioopcion> sumarioopcionList) {
        this.sumarioopcionList = sumarioopcionList;
    }
    
}
