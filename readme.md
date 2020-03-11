# TestUnitaire
An Example of unit test in Java 

(P.S : All copyrights to jderuette's course of study (https://github.com/jderuette))

## To start a UnitTest you need to :

### Retreive the code 

- create a maven project on your eclipse (check the first case "create a simple project")
- in src/main/java, create a package Utils(you can name it whatever you want)
- in this package, create a class "StringUtils"
- Add the following code:


```java
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
```
- Save the file

#### Install JUnit 

- In your pom.xml file, add the following code : 

```java
<dependencies>
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.5</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>

</dependencies>
 ```
 
#### First UnitTest : 
 
  - In src/test/java,create a package with exactly the same name as the package in which you created StringUtils.
  - Create a StringUtilsTest class there.
  - Add in the following code :
  
  ```java
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
```
### Execution on Eclipse 

- Right click on the StringUtilsTest file -> Runs As -> JUnit Test

### Understand the UnitTest code :

At the end of the class, there are two small methods that appear to be utility methods. They are not tests because they do not have the @Test annotation and their naming does not give the impression that they are test methods.

```java
 private Integer countNbPipe(String data) {
        int count = org.apache.commons.lang3.StringUtils.countMatches(data, "|");
        return count;
    }
    private Integer countNbTableStart(String data) {
        int count = org.apache.commons.lang3.StringUtils.countMatches(data,
                "---------------------------------------------------------");
        return count;
    }
```

Both count the number of occurrences of a chain in another chain. 
They are based on a SpringFramework library which is also called StringUtils. To refer to it, we were therefore obliged to specify the fully qualified name of the class.

### Understanding the utility class code

The "toTable (...)" method that we are testing is used to "Transform a list of text strings into a table readable in a terminal". She is waiting
first parameter, a number "number of columns in the table", as a second parameter, a List "data to transform into an array". A priori, this should transform
Start middle end in (with 3 columns):
________________________________________
| Start | middle | end | And surely something like this if we only ask for 2 columns:
________________________________________
| Start | middle | | end |

### Test
For the test method, there is a classic structure:
•	Initialization
•	Call of the method to check
•	Verifications We also see the use of two utility methods
    public void testToTable_oneCol() {
        List<String> datas = new ArrayList<String>();
        datas.add("le petit chas");
        datas.add("se tient");
        datas.add("sur sa tige");
        
        ```java
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
      ```
      
### Assert format
The Assert (in JUnit) expect 3 parameters:

- a text to display if the assertion is not verified. Optional according to the API, but you must always specify one (under pain of galley)!
- the expected value (or control value)
- the variable that contains a value to check. There are variants that just wait for the control value because there is no comparison to verify the assertion (like assertNotNull).

In this example, the verification code uses variables, surely to facilitate the writing of the other tests, and makes it a little complex to read.
But we can't see anything !!
This is a bit unsettling because, during execution, we don't see the "generated table". This is very common in JUnit: we verify an algorithm, if the verification is successful the test is OK, otherwise it is KO. If the developer needs to "see" what's going on, he should use debug mode (or possibly the logs).

### The Test Algo

The test is also an algorithm. As it is written in Java, it is easily readable. Here is the intention that the developer seems to have:

- If the request has 3 columns on a list of 3 items, I should have only one row.
- I can verify that there is only one table by checking that the table header type (the "-----") is present only once.
- I can check the correct number of cells as follows: one pipe per cell AND one more pipe for each line When preparing my data to be checked, I can use variables in the explanatory texts in the event of assertions that are not validated.

### Check test coverage

Is this unique test enough to guarantee that your code is fully tested?
It can be checked by observing the code coverage. The idea is to "monitor" the code during the execution of the Unit Test (s), to see which lines of code are covered.
It is possible to launch Coverage via a right click -> Coverage as -> Junit Test.
The Coverage view is displayed (by default in the ViewList below). The objective of this view is to indicate the coverage rate of the code, by making it possible to see at the project level, a (sub) package, a class and even a method.
We can see here that your unique Unit Test allows you to achieve 18.2% code coverage on your (small) project. If we observe the toTable method of the StringUtils class, we see that this method is 60.2% covered. In fact, the TU only covers 41 of the 103 instructions included in this method.
If you open the StringUtils file, you will be able to see the coverage line by line.
- In Green: a fully covered line
- In Yellow: a partially covered line
- In Red: an uncovered line
Coverage works by "session". A session keeps the lines of code which have been executed or not. It is possible to record several sessions, and then compare. 
It is also possible to "delete" the session data, in particular to remove all the colors in the files!


