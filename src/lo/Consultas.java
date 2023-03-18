/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chenc
 */
public class Consultas
{
    Tools to = new Tools();
    private Conecta co = new Conecta();
    private Connection cn= co.getConnection();
    
    //Valida si existe ya registrado ese nombre de usuario en administradores
    // si existe regresa true si no existe regrasa false
    public boolean verAdmi(String usuario)
    {
        String sql = "SELECT USUARIO FROM administrador WHERE USUARIO=?";
        
        try
        {
            PreparedStatement psr = cn.prepareStatement(sql);
            psr.setString(1, usuario);
            ResultSet rsr = psr.executeQuery();
            if (rsr.next())
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Eror en la consulta", "Error con la conexión");  
            return true;
        }
    }
    
    //validad contraseña y usuario de administrador
    //regresa true si los datos son correctos y regresa false si los datos son incorrectos
    public boolean logAdmi(String usuario, String pass)
    {
        String sql = "SELECT PASSWORD  FROM administrador WHERE USUARIO ='" + usuario + "'";
        if (verAdmi(usuario))
        {
            try
            {

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                if (pass.equals(to.codifi(rs.getString(1), 2)))
                {
                    to.aviso("Bienvenido al sistema", "Mensaje de éxito");
                    return true;
                } else
                {
                    to.error("El usuario o contraseña son incorrectos", "Mensaje de error");
                    return false;
                }
            } catch (SQLException ex)
            {
                to.error("Error en la base de datos", "Error de conexión");
                return false;
            }
        } else
        {
            to.error("El usuario o contraseña son incorrectos", "Mensaje de error");
            return false;
        }

    }
    
    //Consulta de administradores
    public void consuAdmin(DefaultTableModel mode, String usuario, String usera)
    {
        String sql;
        if (usuario.isEmpty())
        {
            sql = "SELECT RFC, USUARIO, NOMBRE, TELEFONO FROM administrador WHERE USUARIO !='" + usera + "'";
        } else
        {
            sql = "SELECT RFC, USUARIO, NOMBRE, TELEFONO FROM administrador WHERE USUARIO !='" + usera + "'"
                    + " AND (USUARIO ='" + usuario + "'" + " OR NOMBRE LIKE '%" + usuario + "%')";
        }

        String data[] = new String[4];

        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2).toUpperCase();
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                mode.addRow(data);
            }
            if (data[0] == null && !usuario.isEmpty())
            {
                to.error("No se econtro ningun dato con este usuario o nombre", "Error de consulta");
            } else if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }

    
   //Valida si existe ya registrado ese nombre de usuario en empleado
    // si existe regresa true si no existe regrasa false
    public boolean verEmpl(String usuario)
    {
        String sql = "SELECT USUARIO FROM empleado WHERE USUARIO=?";
        
        try
        {
            PreparedStatement psr = cn.prepareStatement(sql);
            psr.setString(1, usuario);
            ResultSet rsr = psr.executeQuery();
            if (rsr.next())
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            to.error("Eror en la consulta", "Error con la conexión");  
            return true;
        }
    }
    //Consulta de empleados
    public void consuEmp(DefaultTableModel mode, String usuario)
    {
        String sql;
        if (usuario.isEmpty())
        {
            sql = "SELECT RFC, USUARIO, NOMBRE, TELEFONO FROM empleado";
        } else
        {
            sql = "SELECT RFC, USUARIO, NOMBRE, TELEFONO FROM empleado WHERE "
                    + " (USUARIO ='" + usuario + "'" + " OR NOMBRE LIKE '%" + usuario + "%')";
        }

        String data[] = new String[4];

        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2).toUpperCase();
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                mode.addRow(data);
            }
            if (data[0] == null && !usuario.isEmpty())
            {
                to.error("No se econtro ningun dato con este usuario o nombre", "Error de consulta");
            } else if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }

    //Consulta de produtos
    public void consuPro(DefaultTableModel mode, String clave,String cate)
    {
        String sql;
        if (clave.isEmpty())
        {
            if (cate.isEmpty())
            {
                sql = "SELECT CLAVE, NOMBRE, CANTIDAD, PRECIO, COSTO FROM producto";
            } else
            {
                sql= "SELECT CLAVE, NOMBRE, CANTIDAD, PRECIO, COSTO FROM producto WHERE "
                        + " CATEGORIA='"+cate+"'";
            }
        } else
        {
            sql = "SELECT CLAVE, NOMBRE, CANTIDAD, PRECIO, COSTO FROM producto WHERE "
                    + " CLAVE='"+clave+"' OR NOMBRE LIKE '%"+clave+"%'";
        }

        String data[] = new String[5];

        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2).toUpperCase();
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                mode.addRow(data);
            }
            if (data[0] == null && !clave.isEmpty())
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            } else if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
    
    public void consuCate(DefaultTableModel mode)
    {
       String sql="SELECT * FROM CATEGORIA";
        String data[] = new String[1];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0]=rs.getString(1);
                mode.addRow(data);
            }
            if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }


    public void consuProv(DefaultTableModel mode)
    {
       String sql="SELECT NOMBRE FROM provedor";
        String data[] = new String[1];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0]=rs.getString(1);
                mode.addRow(data);
            }
            if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }

    public ArrayList consuProvee()
    {
        ArrayList<Proveedor> pro= new ArrayList<Proveedor>();
        int id;
        String nombre;
        String sql="SELECT * FROM provedor";
        String data[] = new String[1];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                id= Integer.valueOf(rs.getString(1));
                nombre = rs.getString(2);
                pro.add(new Proveedor(id,nombre));
            }
            return pro;
        } catch (SQLException ex)
        {
            return null;
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
    
    
    public ArrayList consuCate()
    {
        ArrayList<String> pro= new ArrayList<String>();
        String nombre;
        String sql="SELECT * FROM categoria";
        String data[] = new String[1];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                nombre = rs.getString(1);
                pro.add(nombre);
            }
            return pro;
        } catch (SQLException ex)
        {
            return null;
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }

    public Producto dataPro(String clave)
    {
        String sql = "SELECT * FROM producto WHERE CLAVE='" + clave + "'";
        String c, n, ca;
        int can, id;
        double p, cos;
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {

                c = rs.getString(1);
                n = rs.getString(2);
                can = rs.getInt(3);
                p = rs.getDouble(4);
                cos = rs.getDouble(5);
                ca = rs.getString(6);
                id = rs.getInt(7);

                return new Producto(c, n, can, p, cos, ca, id);
            } else
            {
                to.error("Este produtcto no existe", "Error de consulta");
                return null;
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión en consulta");
            return null;
        }
    }
    
    public void consuDatosAdmin(DefaultTableModel mode,String user)
    {
       String sql="SELECT RFC,USUARIO,NOMBRE,TELEFONO FROM administrador WHERE USUARIO='"+user+"'";
        String data[] = new String[4];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0]=rs.getString(1);
                data[1]=rs.getString(2);
                data[2]=rs.getString(3);
                data[3]=rs.getString(4);
                mode.addRow(data);
            }
            if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
    
    public boolean logEmpl(String usuario, String pass)
    {
        String sql = "SELECT PASSWORD  FROM empleado WHERE USUARIO ='" + usuario + "'";
        if (verEmpl(usuario))
        {
            try
            {

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                if (pass.equals(to.codifi(rs.getString(1), 2)))
                {
                    to.aviso("Bienvenido al sistema", "Mensaje de éxito");
                    return true;
                } else
                {
                    to.error("El usuario o contraseña son incorrectos", "Mensaje de error");
                    return false;
                }
            } catch (SQLException ex)
            {
                to.error("Error en la base de datos", "Error de conexión");
                return false;
            }
        } else
        {
            to.error("El usuario o contraseña son incorrectos", "Mensaje de error");
            return false;
        }

    }
    
    public void consuDatosEmpleado(DefaultTableModel mode,String user)
    {
       String sql="SELECT RFC,USUARIO,NOMBRE,TELEFONO FROM empleado WHERE USUARIO='"+user+"'";
        String data[] = new String[4];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0]=rs.getString(1);
                data[1]=rs.getString(2);
                data[2]=rs.getString(3);
                data[3]=rs.getString(4);
                mode.addRow(data);
            }
            if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
    
    public Empleado dataEmpleado(String user)
    {
        String sql = "SELECT * FROM empleado WHERE USUARIO='" + user + "'";
        String data[] = new String[5];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                return new Empleado(data[0], data[1], data[2], data[3], data[4]);
            } else
            {
                return null;
            }

        } catch (SQLException ex)
        {
            return null;
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
    
    public void consuProdvent(DefaultTableModel mode, String rfc,JLabel t)
    {
        double totalVend=0;
        String sql = "SELECT PR.CLAVE, NM.NOMBRE, PR.CANTIDAD, NM.PRECIO  FROM PRODUCTO NM, PRODVEN PR \n"
                + "WHERE NM.CLAVE=PR.CLAVE \n"
                + "AND PR.RFC='"+rfc+"' \n"
                + "AND PR.FECHA=DATE_FORMAT(NOW(),'%Y-%m-%d')";
        String data[] = new String[4];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                totalVend=totalVend+(Double.valueOf(data[2])*Double.valueOf(data[3]));
                mode.addRow(data);
            }
            t.setText("$ "+totalVend);
            if (data[0] == null)
            {
                to.error("No se econtro ningun dato", "Error de consulta");
            }
        } catch (SQLException ex)
        {
            
        }
    }
    
    public double consuTotalDia()
    {
        double total = 0;
        String sql = "SELECT SUM(TOTAL) FROM VENTA WHERE FECHA=DATE_FORMAT(NOW(),'%Y-%m-%d')";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            total = rs.getDouble(1);

            return total;
        } catch (SQLException ex)
        {
            to.error("No se pudo conectar con la base de datos", "Error de conexión");
            return total;
        }
    }
    
    public double consuTotalSemana()
    {
        double total = 0;
        String sql = "SELECT SUM(TOTAL) FROM VENTA WHERE WEEK(FECHA)=WEEK(NOW())";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            total = rs.getDouble(1);

            return total;
        } catch (SQLException ex)
        {
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
            return total;
        }
    }
    
    public double consuTotalMes(String m, String a)
    {
        double total = 0;
        String sql = "SELECT SUM(TOTAL) FROM VENTA WHERE MONTH(FECHA)="+m+" AND YEAR(FECHA)="+a;
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            total = rs.getDouble(1);

            return total;
        } catch (SQLException ex)
        {
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
            return total;
        }
    }
    
    public double consuTotalAnio(String a)
    {
        double total = 0;
        String sql = "SELECT SUM(TOTAL) FROM VENTA WHERE YEAR(FECHA)="+a;
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            total = rs.getDouble(1);

            return total;
        } catch (SQLException ex)
        {
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
            return total;
        }
    }
    
    public Administrador dataAdmin(String user)
    {
        String sql = "SELECT * FROM administrador WHERE USUARIO='" + user + "'";
        String data[] = new String[5];
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                return new Administrador(data[0], data[1], data[2], data[3], data[4]);
            } else
            {
                return null;
            }

        } catch (SQLException ex)
        {
            return null;
            //to.error("No se pudo conectar con la base de datos", "Error de conexión");
        }
    }
}
