/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Competencia;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;
import modelo.UsuarioRol;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "peticiones")
public class peticiones {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "siexisterol")
    public Boolean siexisterol(@WebParam(name = "nombre") String nombre) {
        Rol rol = new Rol();
        
        ArrayList<Rol> rolesexistentes = rol.getRoles();
        
        for (Rol rols: rolesexistentes) {
            if (rols.getRol().equals(nombre)) {
                return true;
            }
            
        }
        return false;
    }

    
    
    
    @WebMethod(operationName = "estadorol")
    public Boolean estadorol(@WebParam(name = "nombrerol") String nombrerol) {
        Rol rol = new Rol();
        
        ArrayList<Rol> estadospornombre = rol.getRoles();
        
        for (Rol rols: estadospornombre) {
            if (rols.getRol().equals(nombrerol)) {
                return rols.isEstado();
            }
            
        }
        return null;
    }
    
        @WebMethod(operationName = "siexisteComp")
    public Boolean siexisteComp(@WebParam(name = "idcomp") String idcomp) {
        Competencia competencia = new Competencia();
        
        ArrayList<Competencia> competenciasExistentes = competencia.getCompetencias();
        
        for (Competencia comps: competenciasExistentes) {
            if (comps.getCompetencias().equals(idcomp)) {
                return true;
            }
            
        }
        return false;
    }
      private ArrayList<UsuarioRol> bd_tabla_usuario_rol = new ArrayList<>();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    
    @WebMethod(operationName = "siexisterol")
    public Boolean siexisterol(@WebParam(name = "nombre") String nombre) {
        Rol rol = new Rol();
        
        ArrayList<Rol> rolesexistentes = rol.roles;
        
        for (Rol rols: rolesexistentes) {
            if (rols.getRol().equals(nombre)) {
                return true;
            }
            
        }
        return false;
    }
    @WebMethod(operationName = "registrarUsuarioPersonaRol")
public String registrarUsuarioPersonaRol(
        @WebParam(name = "idUsuario") int idUsuario,
        @WebParam(name = "idPersona") int idPersona,
        @WebParam(name = "nombreUsuario") String nombreUsuario,
        @WebParam(name = "passwordUsuario") String passwordUsuario,
        @WebParam(name = "nombrePersona") String nombrePersona,
        @WebParam(name = "apellidoPersona") String apellidoPersona,
        @WebParam(name = "dniPersona") String dniPersona,
        @WebParam(name = "celularPersona") String celularPersona,
        @WebParam(name = "correoPersona") String correoPersona,
        @WebParam(name = "idRol") int idRol,
        @WebParam(name = "nombreRol") String nombreRol,
        @WebParam(name = "estadoRol") boolean estadoRol) {

   
    Persona nuevaPersona = new Persona(idPersona, nombrePersona, apellidoPersona, dniPersona, celularPersona, correoPersona);

   
    Rol nuevoRol = new Rol(idRol, nombreRol, estadoRol);
   
    Usuario nuevoUsuario = new Usuario(idUsuario, idPersona, nombreUsuario, passwordUsuario, nuevaPersona);

   
    if (nuevoUsuario.existeUsuario(nombreUsuario)) {
        return "El usuario ya existe. Por favor, elige otro nombre de usuario.";
    } else {
        // Agregar las instancias a las listas correspondientes
        nuevoUsuario.usuarios.add(nuevoUsuario);
        nuevaPersona.personas.add(nuevaPersona);
        nuevoRol.roles.add(nuevoRol);
        UsuarioRol usuariorol = new UsuarioRol(nuevoUsuario, nuevoRol);
        bd_tabla_usuario_rol.add(usuariorol);

        return "Registro exitoso: Usuario, Persona y Rol registrados correctamente.";
    }
}

    @WebMethod(operationName = "loginUsuario")
    public String loginUsuario(
            @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "passwordUsuario") String passwordUsuario) {

        // Buscar el usuario en la lista
        Usuario usuarioEncontrado = null;
        ArrayList<Usuario> usuario = usuarioEncontrado.usuarios;
        for (Usuario usuario2 : usuario) {
            if (usuario2.getUser().equals(nombreUsuario) && usuario2.getPassword().equals(passwordUsuario)) {
                usuarioEncontrado = usuario2;
                break;
            }
        }

        // Verificar si se encontró el usuario
        if (usuarioEncontrado != null) {
            // Obtener el nombre de usuario
            String nombre = usuarioEncontrado.getPersona().getNombre();

            // Devolver información
            return "Login exitoso. Usuario: " + nombre;
        } else {
            // Usuario no encontrado
            return "Usuario o contraseña incorrectos. Por favor, verifica tus credenciales.";
        }
    }
}
         