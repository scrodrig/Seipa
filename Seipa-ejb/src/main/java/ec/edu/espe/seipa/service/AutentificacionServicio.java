/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.UsuarioFacade;
import ec.edu.espe.seipa.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.*;



/**
 *
 * @author ronny
 */



@Stateless
@LocalBean
public class AutentificacionServicio {
    
    @EJB
    private UsuarioFacade usuarioFacede;
    public Usuario usuarioAutentificar(String nombreUsuario, String clave){
    
        Usuario usuario =this.usuarioFacede.findByName(nombreUsuario);
        if(usuario != null){
            if (usuario.getPass().equals(clave)){
                return usuario;
            }
        }
        return null;
    }
    
}
