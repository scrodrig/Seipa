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
@Table(name = "TIPO_PREGUNTA", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPregunta.findAll", query = "SELECT t FROM TipoPregunta t"),
    @NamedQuery(name = "TipoPregunta.findByIdtipopregunta", query = "SELECT t FROM TipoPregunta t WHERE t.idtipopregunta = :idtipopregunta"),
    @NamedQuery(name = "TipoPregunta.findByTipoPregunta", query = "SELECT t FROM TipoPregunta t WHERE t.tipoPregunta = :tipoPregunta")})
public class TipoPregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIPOPREGUNTA", nullable = false, precision = 38, scale = 0)
    private BigDecimal idtipopregunta;
    @Size(max = 100)
    @Column(name = "TIPO_PREGUNTA", length = 100)
    private String tipoPregunta;
    @OneToMany(mappedBy = "idtipopregunta")
    private List<Pregunta> preguntaList;

    public TipoPregunta() {
    }

    public TipoPregunta(BigDecimal idtipopregunta) {
        this.idtipopregunta = idtipopregunta;
    }

    public BigDecimal getIdtipopregunta() {
        return idtipopregunta;
    }

    public void setIdtipopregunta(BigDecimal idtipopregunta) {
        this.idtipopregunta = idtipopregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipopregunta != null ? idtipopregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPregunta)) {
            return false;
        }
        TipoPregunta other = (TipoPregunta) object;
        if ((this.idtipopregunta == null && other.idtipopregunta != null) || (this.idtipopregunta != null && !this.idtipopregunta.equals(other.idtipopregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.TipoPregunta[ idtipopregunta=" + idtipopregunta + " ]";
    }
    
}
