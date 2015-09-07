/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Docente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SchubertDavid
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> {
    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    
    public Docente findByID(String idDocente){
        try{
            String sql = "SELECT obj FROM DOCENTE obj WHERE obj.id=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, idDocente);
            return (Docente) qry.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
            
    }
    
}
