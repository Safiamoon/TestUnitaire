/**
 * 
 */
package org.hoc.table.utest.isa.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author sms
 *
 */
public class StringUtilsTest {
	@Test
    public void testToTable_oneRow() {
        List<String> datas = new ArrayList<String>();
        datas.add("le petit chas");
        datas.add("se tient");
        datas.add("sur sa tige");

        Integer numberOfCol = 3;
      

        String table = StringUtils.toTable(numberOfCol, datas);
       
        Integer expectedNbRows = 1;

        Assert.assertNotNull("Table should contains datas", table);
        Integer expectedPipe = expectedNbRows * (1 + numberOfCol);
        Assert.assertEquals("Table should contains " + expectedNbRows + " row and " + numberOfCol + " columns",
                expectedPipe, countNbPipe(table));

        Integer numberOfTable = 1;
        Assert.assertEquals("Table should contains " + numberOfTable+" table start", numberOfTable, countNbTableStart(table));
    }
	
	@Test
    public void testToTable_twoRow() {
        List<String> datas = new ArrayList<String>();
        datas.add("le petit chas");
        datas.add("se tient");
        datas.add("sur sa tige");

        Integer numberOfCol = 2;
      

        String table = StringUtils.toTable(numberOfCol, datas);
       
        Integer expectedNbRows = 2;

        Assert.assertNotNull("Table should contains datas", table);
        Integer expectedPipe = expectedNbRows * (1 + numberOfCol);
        Assert.assertEquals("Table should contains " + expectedNbRows + " row and " + numberOfCol + " columns",
                expectedPipe, countNbPipe(table));

        Integer numberOfTable = 1;
        Assert.assertEquals("Table should contains " + numberOfTable+" table start", numberOfTable, countNbTableStart(table));
    }

 private Integer countNbPipe(String data) {
        int count = org.apache.commons.lang3.StringUtils.countMatches(data, "|");
        return count;
    }

    private Integer countNbTableStart(String data) {
        int count = org.apache.commons.lang3.StringUtils.countMatches(data,
                "---------------------------------------------------------");
        return count;
    }


}

