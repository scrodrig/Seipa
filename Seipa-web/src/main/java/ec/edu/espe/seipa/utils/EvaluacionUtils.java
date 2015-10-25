/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.utils;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.Preguntaevaluacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SchubertDavid
 */
public final class EvaluacionUtils {
    
    public static List<Pregunta> getPreguntaListFromEvaluacion(Evaluacion evaluacion){
        List<Pregunta> preguntas=new ArrayList<Pregunta>();
        for (Preguntaevaluacion preguntaEvaluacion : evaluacion.getPreguntaevaluacionList()) {
            if(preguntaEvaluacion.getIdpregunta()!=null){
                preguntas.add(preguntaEvaluacion.getIdpregunta());
            }
        }
        return preguntas;
    }
}
