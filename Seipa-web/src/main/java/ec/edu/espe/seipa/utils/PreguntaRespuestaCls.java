/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.espe.seipa.utils;

import ec.edu.espe.seipa.model.Pregunta;

/**
 *
 * @author abecerra
 * 
 */
public class PreguntaRespuestaCls {
    
    private Pregunta pregunta;
    private String respuesta;

    public PreguntaRespuestaCls() {
    }

    public PreguntaRespuestaCls(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
