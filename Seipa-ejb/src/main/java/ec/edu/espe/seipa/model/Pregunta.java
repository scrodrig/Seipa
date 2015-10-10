/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PREGUNTA", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdpregunta", query = "SELECT p FROM Pregunta p WHERE p.idpregunta = :idpregunta"),
    @NamedQuery(name = "Pregunta.findByTextopregunta", query = "SELECT p FROM Pregunta p WHERE p.textopregunta = :textopregunta"),
    @NamedQuery(name = "Pregunta.findByOrdernumber", query = "SELECT p FROM Pregunta p WHERE p.ordernumber = :ordernumber")})
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPREGUNTA", nullable = false, precision = 38, scale = 0)
    private BigDecimal idpregunta;
    @Size(max = 255)
    @Column(name = "TEXTOPREGUNTA", length = 255)
    private String textopregunta;
    @Column(name = "ORDERNUMBER")
    private BigInteger ordernumber;
    @ManyToMany(mappedBy = "preguntaList")
    private List<Evaluacion> evaluacionList;
    @ManyToMany(mappedBy = "preguntaList")
    private List<Opcion> opcionList;
    @JoinColumn(name = "IDTIPOPREGUNTA", referencedColumnName = "IDTIPOPREGUNTA")
    @ManyToOne
    private TipoPregunta idtipopregunta;

    public Pregunta() {
    }

    public Pregunta(BigDecimal idpregunta) {
        this.idpregunta = idpregunta;
    }

    public BigDecimal getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(BigDecimal idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getTextopregunta() {
        return textopregunta;
    }

    public void setTextopregunta(String textopregunta) {
        this.textopregunta = textopregunta;
    }

    public BigInteger getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(BigInteger ordernumber) {
        this.ordernumber = ordernumber;
    }

    @XmlTransient
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @XmlTransient
    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    public TipoPregunta getIdtipopregunta() {
        return idtipopregunta;
    }

    public void setIdtipopregunta(TipoPregunta idtipopregunta) {
        this.idtipopregunta = idtipopregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpregunta != null ? idpregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idpregunta == null && other.idpregunta != null) || (this.idpregunta != null && !this.idpregunta.equals(other.idpregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Pregunta[ idpregunta=" + idpregunta + " ]";
    }
    
}
