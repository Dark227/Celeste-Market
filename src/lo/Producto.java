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
public class Producto
{
    private String clave;
    private String nombre;
    private int cantidad;
    private double precio;
    private double costo;
    private String categoria;
    private int idpr;

    public Producto(String clave, String nombre, int cantidad, double precio, double costo, String categoria, int idpr)
    {
        this.clave = clave;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
        this.categoria = categoria;
        this.idpr = idpr;
    }

    
    /**
     * @return the clave
     */
    public String getClave()
    {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave)
    {
        this.clave = clave;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad()
    {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    /**
     * @return the precio
     */
    public double getPrecio()
    {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    /**
     * @return the costo
     */
    public double getCosto()
    {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo)
    {
        this.costo = costo;
    }

    /**
     * @return the categoria
     */
    public String getCategoria()
    {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    /**
     * @return the idpr
     */
    public int getIdpr()
    {
        return idpr;
    }

    /**
     * @param idpr the idpr to set
     */
    public void setIdpr(int idpr)
    {
        this.idpr = idpr;
    }
    
}
