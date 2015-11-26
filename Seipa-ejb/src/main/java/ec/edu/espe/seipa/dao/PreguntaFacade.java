/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.TipoPregunta;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PreguntaFacade extends AbstractFacade<Pregunta> {

    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaFacade() {
        super(Pregunta.class);
    }

    public List<Pregunta> getPreguntaByEvaluacion(Evaluacion evaluacion) {
        try {

            Pregunta objPregunta;
            TipoPregunta objTipoPregunta;
            List<Pregunta> lsPegunta = new ArrayList();
            String sql = "SELECT p.* FROM BI.PREGUNTA p join BI.PREGUNTAEVALUACION pe on p.idpregunta=pe.idpregunta WHERE pe.idevaluacion=" + evaluacion.getIdevaluacion();
            Query qry = em.createNativeQuery(sql);
            List<Object[]> p1 = qry.getResultList();
            if (p1.size() >= 1) {
                for (int i = 0; i < p1.size(); i++) {
                    objPregunta = new Pregunta();
                    objTipoPregunta = new TipoPregunta();
                    objPregunta.setIdpregunta(new BigDecimal(p1.get(i)[0].toString()));
                    objTipoPregunta.setTipoPregunta(p1.get(i)[1].toString());
                    objPregunta.setIdtipopregunta(objTipoPregunta);
                    objPregunta.setTextopregunta(p1.get(i)[2].toString());
                    objPregunta.setOrdernumber(new BigInteger(p1.get(i)[3].toString()));
                    lsPegunta.add(objPregunta);
                }
            }
            return lsPegunta;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pregunta findByIdEvaluacion(String idEvaluacion) {
        try {
            String sql = "SELECT obj FROM PreguntaEvaluacion obj WHERE obj.idEvaluacion=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, idEvaluacion);
            return (Pregunta) qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public String findIdPregunta() {
        try {
            String codigoNuevo;
            Query qry = em.createNativeQuery("select max(IDPREGUNTA) from BI.PREGUNTA");
            codigoNuevo = qry.getSingleResult().toString();
            return (codigoNuevo);
        } catch (NoResultException e) {
            return null;
        }
    }

    public String orderNumbrePregunta(String evaluacion) {
        try {
            String orderNumber;
            String sql = "select max(ordernumber) from BI.PREGUNTA p join BI.PREGUNTAEVALUACION pe on p.IDPREGUNTA=pe.IDPREGUNTA where pe.IDEVALUACION = " + evaluacion;
            Query qry = em.createNativeQuery(sql);
            if (qry.getSingleResult() == null) {
                orderNumber = "0";
            } else {
                orderNumber = qry.getSingleResult().toString();
            }
            return orderNumber;
        } catch (NoResultException e) {
            return null;
        }
    }
}
