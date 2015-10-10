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
@Table(name = "RESULTADOS_HETEROGENEAS", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadosHeterogeneas.findAll", query = "SELECT r FROM ResultadosHeterogeneas r"),
    @NamedQuery(name = "ResultadosHeterogeneas.findById", query = "SELECT r FROM ResultadosHeterogeneas r WHERE r.id = :id"),
    @NamedQuery(name = "ResultadosHeterogeneas.findByResultado", query = "SELECT r FROM ResultadosHeterogeneas r WHERE r.resultado = :resultado")})
public class ResultadosHeterogeneas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "RESULTADO", precision = 126)
    private Double resultado;
    @JoinColumn(name = "ID_EVALUACION_DOCENTE", referencedColumnName = "ID")
    @ManyToOne
    private Evaluacion idEvaluacionDocente;

    public ResultadosHeterogeneas() {
    }

    public ResultadosHeterogeneas(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Evaluacion getIdEvaluacionDocente() {
        return idEvaluacionDocente;
    }

    public void setIdEvaluacionDocente(Evaluacion idEvaluacionDocente) {
        this.idEvaluacionDocente = idEvaluacionDocente;
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
        if (!(object instanceof ResultadosHeterogeneas)) {
            return false;
        }
        ResultadosHeterogeneas other = (ResultadosHeterogeneas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.ResultadosHeterogeneas[ id=" + id + " ]";
    }
    
}
