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
public class Modifica
{
    Tools to = new Tools();
    private Conecta co = new Conecta();
    private Connection cn= co.getConnection();
    
    
    public boolean addPro(int ca,String clave)
    {
        String sql="UPDATE producto SET CANTIDAD="+ca+" WHERE CLAVE='"+clave+"'";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public boolean quitaPro(int ca,String clave)
    {
        String sql="UPDATE producto SET CANTIDAD="+ca+" WHERE CLAVE='"+clave+"'";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public  boolean modifiPassEmple(String user, String pass)
    {
        String sql="UPDATE empleado SET PASSWORD='"+pass+"' WHERE USUARIO='"+user+"'";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public  boolean modifiPassAdmin(String user, String pass)
    {
        String sql="UPDATE administrador SET PASSWORD='"+pass+"' WHERE USUARIO='"+user+"'";
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public  boolean modifiDataAdmin(String rfc, String user,String name, String tele)
    {
        String sql;
        if (rfc.isEmpty())
        {
            sql="UPDATE administrador SET NOMBRE='"+name+"', TELEFONO='"+tele+"' WHERE USUARIO='"+user+"'";
        } else
        {
            sql="UPDATE administrador SET NOMBRE='"+name+"', TELEFONO='"+tele+"' "
                    + ", RCF='"+rfc+"' WHERE USUARIO='"+user+"'";
        }
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public  boolean modifiDataEmpleado(String rfc, String rfc2,String name, String tele)
    {
        String sql;
        if (rfc.isEmpty())
        {
            sql="UPDATE empleado SET NOMBRE='"+name+"', TELEFONO='"+tele+"' WHERE RFC='"+rfc2+"'";
        } else
        {
            sql="UPDATE empleado e, prodven p, venta v SET e.NOMBRE='"+name+"', e.TELEFONO='"+tele+"' "
                    + ", e.RCF='"+rfc+"', p.RCF='"+rfc+"', v.RFC='"+rfc+"' WHERE e.RFC='"+rfc2+"' AND"
                    + " p.RFC='"+rfc2+"' AND v.RFC='"+rfc2+"'";
        }
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
    
    public  boolean modifiDataPro(String clave, String clave2,String name, int cantidad
            ,double precio,double costo, String categoria, int id)
    {
        String sql;
        if (clave.isEmpty())
        {
            sql="UPDATE producto SET NOMBRE='"+name+"', CANTIDAD="+cantidad+","
                    + " PRECIO="+precio+", COSTO="+costo+","
                    + " CATEGORIA='"+categoria+"', IDPROVEDOR="+id+" WHERE CLAVE='"+clave2+"'";
        } else
        {
            sql="UPDATE producto SET CLAVE='"+clave+"', NOMBRE='"+name+"', CANTIDAD="+cantidad+","
                    + " PRECIO="+precio+", COSTO="+costo+","
                    + " CATEGORIA='"+categoria+"', IDPROVEDOR="+id+" WHERE CLAVE='"+clave2+"'";
        }
        
        try
        {
            PreparedStatement post = cn.prepareStatement(sql);
            int n= post.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Error en la base de datos", "Error de conexión");
            return false;
        }
    }
}
