/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Archivos;
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
public class ArchivosFacade extends AbstractFacade<Archivos> {

    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArchivosFacade() {
        super(Archivos.class);
    }

    public String findIDLogro() {
        try {
            String codigo;
            Query qry = em.createNativeQuery("select max(ID) from BI.ARCHIVOS");
            if (qry.getSingleResult() == null) {
                codigo = "0";
            } else {
                codigo = qry.getSingleResult().toString();
            }
            return (codigo);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Archivos> findByIdDocente(String idDocente) {
        try {
            Archivos objArchivos;
            List<Archivos> lsArchivos = new ArrayList();
            String sql = "SELECT a.* FROM BI.ARCHIVOS a WHERE a.idDocente=" + idDocente;
            Query qry = em.createNativeQuery(sql);
            List<Object[]> p1 = qry.getResultList();
            if (p1.size() >= 1) {
                for (int i = 0; i < p1.size(); i++) {
                    objArchivos = new Archivos();
                    objArchivos.setId(new BigDecimal(p1.get(i)[0].toString()));
                    objArchivos.setNombre(p1.get(i)[1].toString());
                    objArchivos.setExtension(p1.get(i)[2].toString());
                    objArchivos.setUrl(p1.get(i)[3].toString());
                    objArchivos.setIddocente(idDocente);
                    lsArchivos.add(objArchivos);
                }
            }
            return lsArchivos;
        } catch (NoResultException e) {
            return null;
        }

    }
}
