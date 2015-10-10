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
@Table(name = "RESULTADO", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultado.findAll", query = "SELECT r FROM Resultado r"),
    @NamedQuery(name = "Resultado.findByIdresultado", query = "SELECT r FROM Resultado r WHERE r.idresultado = :idresultado"),
    @NamedQuery(name = "Resultado.findByValorResultado", query = "SELECT r FROM Resultado r WHERE r.valorResultado = :valorResultado")})
public class Resultado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRESULTADO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idresultado;
    @Size(max = 100)
    @Column(name = "VALOR_RESULTADO", length = 100)
    private String valorResultado;
    @JoinColumn(name = "IDOPCION", referencedColumnName = "IDOPCION")
    @ManyToOne
    private Opcion idopcion;

    public Resultado() {
    }

    public Resultado(BigDecimal idresultado) {
        this.idresultado = idresultado;
    }

    public BigDecimal getIdresultado() {
        return idresultado;
    }

    public void setIdresultado(BigDecimal idresultado) {
        this.idresultado = idresultado;
    }

    public String getValorResultado() {
        return valorResultado;
    }

    public void setValorResultado(String valorResultado) {
        this.valorResultado = valorResultado;
    }

    public Opcion getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Opcion idopcion) {
        this.idopcion = idopcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresultado != null ? idresultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.idresultado == null && other.idresultado != null) || (this.idresultado != null && !this.idresultado.equals(other.idresultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Resultado[ idresultado=" + idresultado + " ]";
    }
    
}
