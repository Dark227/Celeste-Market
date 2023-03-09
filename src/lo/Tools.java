/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo;

import java.awt.Color;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import lib.TextPrompt;

/**
 *
 * @author chenc
 */
public class Tools
{
    public Icon setIconoAL(String url, JLabel boton)
      {
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int ancho = boton.getWidth();
        int alto = boton.getHeight();
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
      }
    public Image icono()
    {
        Image icon = new ImageIcon(getClass().getResource("/img/logoTienda.jpg")).getImage();
        return icon;
    }
    
    public void txtFondo(String s, JTextField t)
    {
        TextPrompt usuario = new TextPrompt(s, t);
    }
    /**
     *Cambiar color de header tabla
     * t= nombre de tabla
     * n= numero de columnas
     * @param t
     * @param n 
     */
    public void colorTable(JTable t,int n)
      {
        DefaultTableCellRenderer enc = new DefaultTableCellRenderer();
        enc.setBackground(color(1));
        enc.setForeground(Color.WHITE);
          for (int i = 0; i < n; i++)
            {
              TableColumn column = t.getTableHeader().getColumnModel().getColumn(i);
              column.setHeaderRenderer(enc);
            }
      }
    /**
     * i=1 color azul i=2 color gris i=3 color verde i=4 color rojo
     *
     * @param i
     * @return
     */
    public Color color(int i)
      {
        Color c = null;
        switch (i)
          {
            case 1:
                c = new Color(0, 118, 255, 250);
                break;
            case 2:
                c = new Color(227, 230, 232, 100);
                break;
            case 3:
                c = new Color(50, 204, 8, 100);
                break;
            case 4:
                c = new Color(226, 15, 0, 100);
                break;
            case 5:
                c = new Color(255, 255, 255);
                break;
          }
        return c;
      }
    public void error(String mensa, String titu)
      {
        JOptionPane.showMessageDialog(null, mensa, titu, JOptionPane.ERROR_MESSAGE);
      }
    public void aviso(String mensa, String titu)
      {
        JOptionPane.showMessageDialog(null, mensa, titu, JOptionPane.INFORMATION_MESSAGE);
      }

    public int pregunta(String mensa, String titu)
      {
        int r;
        r = JOptionPane.showConfirmDialog(null, mensa, titu, JOptionPane.YES_NO_OPTION);
        return r;
      }
    
    /**
     * imprimir ticket
     * @param jTable
     * @param header
     * @param footer
     * @param showPrintDialog 
     */
    public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog)
      {
        boolean fitWidth = true;
        boolean interactive = true;
        // We define the print mode (Definimos el modo de impresión)
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try
          {
            // Print the table (Imprimo la tabla)             
            boolean complete = jTable.print(mode,
                    new MessageFormat(header),
                    new MessageFormat(footer),
                    showPrintDialog,
                    null,
                    interactive);
            if (complete)
              {
                // Mostramos el mensaje de impresión existosa
                JOptionPane.showMessageDialog(jTable,
                        "Print complete (Impresión completa)",
                        "Print result (Resultado de la impresión)",
                        JOptionPane.INFORMATION_MESSAGE);
              } else
              {
                // Mostramos un mensaje indicando que la impresión fue cancelada                 
                JOptionPane.showMessageDialog(jTable,
                        "Print canceled (Impresión cancelada)",
                        "Print result (Resultado de la impresión)",
                        JOptionPane.WARNING_MESSAGE);
              }
          } catch (PrinterException pe)
          {
            JOptionPane.showMessageDialog(jTable,
                    "Print fail (Fallo de impresión): " + pe.getMessage(),
                    "Print result (Resultado de la impresión)",
                    JOptionPane.ERROR_MESSAGE);
          }
      }
    /**
     * codifica con i=1 descodifica con i=2
     *
     * @param str
     * @param i
     * @return
     */
    public String codifi(String str, int i)
      {
        Encrip des = new Encrip();
        String co="";
        des.setCadena(str);
        if (i == 1)
          {
            co = des.ecnode();
          } else if (i == 2)
          {
            co = des.deecnode();
          }
        return co;
      }
}
