/**
 * 
 */
package org.hoc.table.utest.isa.utils;
import java.util.List;

/**
 * @author sms
 *
 */
public final class StringUtils {

    /**
     * Utility Class
     */
    private StringUtils() {

    }

    /**
     * Transforme une liste de chaines de texte en un tableau lisible dans un terminal
     * @param nbCol nombre de colonnes du tableau
     * @param datas données à transformer en tableau
     * @return
     */
    public static String toTable(Integer nbCol, List<String> datas) {
        StringBuilder sb = new StringBuilder();
       
        if (null != datas && datas.size() > 0 && nbCol > 0) {
            Integer remaningCols = nbCol;
            sb.append("---------------------------------------------------------");
            sb.append(System.getProperty("line.separator"));
            sb.append("|");
            for (String cellContent : datas) {
                if (remaningCols > 0) {
                    sb.append(cellContent);
                    sb.append("|");
                    remaningCols--;
                } else {
                    sb.append(System.getProperty("line.separator"));
                    sb.append("|");
                    sb.append(cellContent);
                    sb.append("|");
                    //reset for new line
                    remaningCols = nbCol-1;
                }
            }
           
            //check if last row is missing some columns
            if(remaningCols > 0) {
                while(remaningCols > 0) {
                    sb.append(" ");
                    sb.append("|");
                    remaningCols--;
                }
            }
        }

        return sb.toString();
    }
}