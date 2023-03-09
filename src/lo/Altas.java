/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chenc
 */
public class Altas
{
    Tools to = new Tools();
    private Conecta co = new Conecta();
    private Connection cn= co.getConnection();
    private Consultas cl = new Consultas();
    
    public boolean altaAdmin(String rfc, String usuario, String pass, String nombre, String telefono)
    {
        String sql = "INSERT INTO administrador(RFC, USUARIO, PASSWORD, NOMBRE, TELEFONO) VALUES (?,?,?,?,?)";
        
        try
        {
            if (cl.verAdmi(usuario))
            {
                to.error("Este nombre de usuario ya esta en uso", "Error de registró");
                return false;
            } else
            {
                
                PreparedStatement post = cn.prepareStatement(sql);
                post.setString(1, rfc);
                post.setString(2, usuario);
                post.setString(3, to.codifi(pass, 1));
                post.setString(4, nombre);
                post.setString(5, telefono);
                int n = post.executeUpdate();
                if (n > 0)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean altaEmpl(String rfc, String usuario, String pass, String nombre, String telefono)
    {
        String sql = "INSERT INTO empleado(RFC, USUARIO, PASSWORD, NOMBRE, TELEFONO) VALUES (?,?,?,?,?)";
        
        try
        {
            if (cl.verEmpl(usuario))
            {
                to.error("Este nombre de usuario ya esta en uso", "Error de registró");
                return false;
            } else
            {
                
                PreparedStatement post = cn.prepareStatement(sql);
                post.setString(1, rfc);
                post.setString(2, usuario);
                post.setString(3, to.codifi(pass, 1));
                post.setString(4, nombre);
                post.setString(5, telefono);
                int n = post.executeUpdate();
                if (n > 0)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean altaCate(String cat)
    {
        String sql ="INSERT INTO categoria (CATEGORIA) VALUES (?)";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            post.setString(1, cat.toUpperCase());
            int n = post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                to.error("No se pudo registrar la categoría", "Error de registro");
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean altaPro(String clave, String nombre,int cantidad,double precio,double costo,
            String categoria,int id )
    {
        String sql="INSERT INTO producto ( CLAVE ,  NOMBRE, CANTIDAD, PRECIO, COSTO, "
                + "CATEGORIA, IDPROVEDOR) VALUES (?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            post.setString(1, clave);
            post.setString(2, nombre);
            post.setInt(3, cantidad);
            post.setDouble(4, precio);
            post.setDouble(5, costo);
            post.setString(6, categoria);
            post.setInt(7, id);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                to.error("No se registro el producto", "Error de registro");
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean AltaProve(String nombre)
    {
        String sql ="INSERT INTO provedor (NOMBRE) VALUES (?)";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            post.setString(1, nombre.toUpperCase());
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                to.error("No se registro el proveedor", "Error de registro");
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean altaProdven(String rfc, String clave,String fecha)
    {
        String sql;
        if (fecha.isEmpty())
        {
            sql="INSERT INTO prodven (RFC, CLAVE, FECHA) VALUES (?,?,now())";
        } else
        {
            sql="INSERT INTO prodven (RFC, CLAVE, FECHA) VALUES (?,?,?)";
        }
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            post.setString(1, rfc);
            post.setString(2, clave);
            if (!fecha.isEmpty())
            {
                post.setString(3, fecha);
            }
            
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                to.error("No se registro de producto de venta", "Error de registro");
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean  altaVenta(String rfc, double total, String fecha)
    {
        String sql;
        if (fecha.isEmpty())
        {
            sql="INSERT INTO venta (RFC, TOTAL, FECHA) VALUES (?,?,now())";
        } else
        {
            sql="INSERT INTO venta (RFC, TOTAL, FECHA) VALUES (?,?,?)";
        }
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            post.setString(1, rfc);
            post.setDouble(2, total);
            if (!fecha.isEmpty())
            {
                post.setString(3, fecha);
            }
            
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                to.error("No se registro de la venta", "Error de registro");
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error con la base de datos", "Error de conexión");
            return false;
        }
    }
}
