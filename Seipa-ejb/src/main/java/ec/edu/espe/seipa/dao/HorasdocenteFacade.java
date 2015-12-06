/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Horasdocente;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class HorasdocenteFacade extends AbstractFacade<Horasdocente> {

    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorasdocenteFacade() {
        super(Horasdocente.class);
    }

    public List<Horasdocente> HorasbyDocente(Docente docente) {
        try {

            Horasdocente objHorasDocente;
            List<Horasdocente> lsHoras = new ArrayList();
            String sql = "SELECT h.* FROM BI.HORASDOCENTE h WHERE h.iddocente=" + docente.getId();
            Query qry = em.createNativeQuery(sql);
            List<Object[]> p1 = qry.getResultList();
            if (p1.size() >= 1) {
                for (int i = 0; i < p1.size(); i++) {
                    objHorasDocente = new Horasdocente();
                    objHorasDocente.setIdhorasdocente(new BigDecimal(p1.get(i)[0].toString()));
                    objHorasDocente.setHoras(new Double(p1.get(i)[1].toString()));
                    objHorasDocente.setTipohoras(p1.get(i)[2].toString());
                    objHorasDocente.setIddocente(docente);
                    lsHoras.add(objHorasDocente);
                }
            }
            return lsHoras;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public String findIdHorasDocente() {
        try {
            String codigoNuevo;
            Query qry = em.createNativeQuery("select max(IDHORASDOCENTE) from BI.HORASDOCENTE");
            codigoNuevo = qry.getSingleResult().toString();
            return (codigoNuevo);
        } catch (NoResultException e) {
            return null;
        }
    }

}
