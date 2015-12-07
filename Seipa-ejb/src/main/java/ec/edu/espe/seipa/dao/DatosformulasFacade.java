/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Datosformulas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ronny
 */
@Stateless
public class DatosformulasFacade extends AbstractFacade<Datosformulas> {
    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosformulasFacade() {
        super(Datosformulas.class);
    }
    
    public Datosformulas findByEstado(String idEstado) {
        try {
            String sql = "SELECT d FROM Datosformulas d WHERE d.estadoparametro =?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, idEstado);
            return (Datosformulas) qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public String findIdDatosFormula() {
        try {
            String codigoNuevo;
            Query qry = em.createNativeQuery("select max(IDDATOSFORMULA) from BI.DATOSFORMULAS");
            codigoNuevo = qry.getSingleResult().toString();
            return (codigoNuevo);
        } catch (NoResultException e) {
            return null;
        }
    }
}
