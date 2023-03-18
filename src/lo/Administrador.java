/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo;

/**
 *
 * @author chenc
 */
public class Administrador
{
  String rfc;
  String usuario;
  String password;
  String nombre;
  String telefono;

    public Administrador(String rfc, String usuario, String password, String nombre, String telefono)
    {
        this.rfc = rfc;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
    }

  
    public String getRfc()
    {
        return rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
    
}
