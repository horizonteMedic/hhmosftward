/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autocomplete;

import Clases.clsConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author usuario
 */
public class autoComplete {
    /**
    * Permite escribir textos dentro de un combox y de forma automatica va selecccionando el texto que queremos
    * consultar. Los datos se cargan a partir de un vector String[].
    * @param comboBox - Asociado
    * @param veDatos - Datos para el combox
    */
    public static void setAutoComplete(JComboBox comboBox, String[]veDatos)
    {
        comboBox.setEditable(true);
        comboBox.setModel(new javax.swing.DefaultComboBoxModel(veDatos));
        AutoCompleteDecorator.decorate(comboBox);
    }

/**
     * Segun nuestra consulta SQL la cantidad de rows que retorna, ese resultado lo convierte en un String[]. Este metodo, solamente toma la primera columna.
     * @param sql
     * @return
     */
    public static String[]getColumnToVector(String sql)
    {
        String[]veDatos;
        clsConnection oConn = new clsConnection();
      
        try
        {
           oConn.FnBoolQueryExecute(sql);
            ArrayList lista = new ArrayList();
            while (oConn.setResult.next())
            {
                lista.add(oConn.setResult.getString(1));
            }
            veDatos = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++)
            {
                veDatos[i]=lista.get(i).toString();
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
            oConn.SubConnectionClose();
            return null;
        }
        return veDatos;
    }
}
