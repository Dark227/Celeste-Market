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
public class Eliminar
{
    Tools to = new Tools();
    private Conecta co = new Conecta();
    private Connection cn= co.getConnection();
    
    public boolean eliminaAdmin(String rfc)
    {
        String sql ="DELETE FROM administrador WHERE RFC='"+rfc+"'";
        try
        {
            PreparedStatement pst = cn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Eror en la conexión", "Error con la conexión"); 
            return false;
        }
    }
    
    public boolean eliminaEmpleado(String rfc)
    {
        String sql ="DELETE FROM empleado WHERE RFC='"+rfc+"'";
        try
        {
            PreparedStatement pst = cn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Eror en la conexión", "Error con la conexión"); 
            return false;
        }
    }
    
    public boolean eliminaProducto(String clave)
    {
        String sql ="DELETE FROM producto WHERE CLAVE='"+clave+"'";
        try
        {
            PreparedStatement pst = cn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if (n>0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Eror en la conexión", "Error con la conexión"); 
            return false;
        }
    }
}
