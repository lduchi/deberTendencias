/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Clasificacion;
import modelo.Competencia;
import modelo.Persona;
import modelo.Producto;
import modelo.Proveedores;
import modelo.Rol;
import modelo.Usuario;
import modelo.UsuarioRol;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "peticiones")
public class peticiones {
    List<Persona> listaPersonas = new ArrayList<>();
    List<Rol> listaRoles = new ArrayList<>();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param nombre
     * @return 
     */
   

    
    
    
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

   String rol="";
    Usuario usuarioEncontrado = null;
    for (UsuarioRol usuario : bd_tabla_usuario_rol) {
        if (usuario.getId_usuario().getUser().equals(nombreUsuario) && usuario.getId_usuario().getPassword().equals(passwordUsuario)) {
            usuarioEncontrado = usuario.getId_usuario();
            rol=usuario.getId_rol().getRol();
            break;
        }
    }

    // Verificar si se encontró el usuario
    if (usuarioEncontrado != null) {
        // Obtener el nombre de usuario
        String nombre = usuarioEncontrado.getPersona().getNombre();

        // Devolver información
        return "Login exitoso. Usuario: " + nombre+" y su rol es: "+ rol;
    } else {
        // Usuario no encontrado
        return "Usuario o contraseña incorrectos. Por favor, verifica tus credenciales.";
    }
}
    @WebMethod(operationName = "buscarP")
    public String buscarP(@WebParam(name = "dni") String dni) {
        for (Persona persona : listaPersonas) {
            if (dni.equals(persona.getDni())) {
                dni=persona.getDni();
                break;
            }
        }
        return dni;
    }

    @WebMethod(operationName = "nombreCompl")
    public String nombreCompl(@WebParam(name = "nombreCompleto") String nombreCompleto) {
        String nombre = null;
        for (Persona persona : listaPersonas) {
            if (nombreCompleto.equals(persona.getNombre()+persona.getApellido())) {
                nombre = persona.getNombre()+persona.getApellido();
                break;
            }
        }
        return nombre;
    }
    
        @WebMethod(operationName = "crearProducto")
    public boolean crearProducto(
            @WebParam(name = "idProducto") int idProducto,
            @WebParam(name = "stock") int stock,
            @WebParam(name = "precioUnitario") double precioUnitario,
            @WebParam(name = "unidad") String unidad,
            @WebParam(name = "idClasificacion") Clasificacion idClasificacion,
            @WebParam(name = "idProveedor") Proveedores idProveedor,
            @WebParam(name = "iva") boolean iva) {

        Producto nuevoProducto = new Producto(idProducto, stock, precioUnitario, unidad, idClasificacion, idProveedor, iva);

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(nuevoProducto);

        return true;
    }
}
         