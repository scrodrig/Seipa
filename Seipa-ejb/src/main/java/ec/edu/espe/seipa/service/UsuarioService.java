/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.UsuarioFacade;
import ec.edu.espe.seipa.model.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author SchubertDavid
 */
@Stateless
@LocalBean
public class UsuarioService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UsuarioFacade usuarioFacade;
    
     public Usuario usuarioAutentificar(String nombreUsuario, String clave) {
        Usuario usuario;
        return null;
    }
}
