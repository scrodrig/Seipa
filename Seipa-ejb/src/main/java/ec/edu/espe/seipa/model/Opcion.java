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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "OPCION", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByIdopcion", query = "SELECT o FROM Opcion o WHERE o.idopcion = :idopcion"),
    @NamedQuery(name = "Opcion.findByValorOpcion", query = "SELECT o FROM Opcion o WHERE o.valorOpcion = :valorOpcion")})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDOPCION", nullable = false, precision = 38, scale = 0)
    private BigDecimal idopcion;
    @Size(max = 100)
    @Column(name = "VALOR_OPCION", length = 100)
    private String valorOpcion;
    @JoinTable(name = "PREGUNTAOPCION", joinColumns = {
        @JoinColumn(name = "IDOPCION", referencedColumnName = "IDOPCION")}, inverseJoinColumns = {
        @JoinColumn(name = "IDPREGUNTA", referencedColumnName = "IDPREGUNTA")})
    @ManyToMany
    private List<Pregunta> preguntaList;
    @OneToMany(mappedBy = "idopcion")
    private List<Resultado> resultadoList;

    public Opcion() {
    }

    public Opcion(BigDecimal idopcion) {
        this.idopcion = idopcion;
    }

    public BigDecimal getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(BigDecimal idopcion) {
        this.idopcion = idopcion;
    }

    public String getValorOpcion() {
        return valorOpcion;
    }

    public void setValorOpcion(String valorOpcion) {
        this.valorOpcion = valorOpcion;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopcion != null ? idopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Opcion[ idopcion=" + idopcion + " ]";
    }
    
}
