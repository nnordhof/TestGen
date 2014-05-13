/*
 * The Testdatabase Class holds all instances of test objects
 * @author Grant Pickett, Yuliya Levitskaya
 * @version 5/13/2014
 */

package testtool.models.testdb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import testtool.models.questiondb.Question;
import testtool.models.questiondb.QuestionDatabank;
/**
 * This class manages and stores Tests. The test parameter is a selected test.
 */
public class TestDatabase {
   public TestDatabase(){
    Charset charset = Charset.forName("US-ASCII");
    File file = new File("database.txt");
    try (BufferedReader reader = new BufferedReader(
        new FileReader(file.getAbsoluteFile()))) {
        String line = null;
        ArrayList<Question> questions = null;
        HashMap<String, String> params = null;
        String[] parts = new String[4];
        while ((line = reader.readLine()) != null) {
          if(line.equals("test")){
            questions = new ArrayList<Question>();
            params = new HashMap<String, String>();
          }
          parts = line.split(" ");
          if(parts.length == 4) {
            params.put(parts[1], parts[3]);
          }
          else {
            questions.add(QuestionDatabank.parseQuestion(line));
          }
          if(line.equals("endtest")){
              createTest(params, questions);
          }
        }
    } catch (IOException x) {
        System.err.format("IOException: %s%n", x);
    }
   }
   /**
    * The collection of Test Objects
    */
   static ArrayList<Test> tests = new ArrayList<Test>();
   static Integer         idPos = 0;
   /**
    * This method will begin the process of creating a new test
    */
   /*
    * @ ensures // // The generation dialogue will appear //
    */
   public void createTest(HashMap<String, String> data,
      ArrayList<Question> questionL) {
      idPos += 1;
      data.put("uniqueId", idPos.toString());
      final Test newTest = new Test(data, questionL);
      TestDatabase.tests.add(newTest);
      System.out.println("in TestDatabase.createTest");
      save();
   }
   /**
    * This method will begin the process of editing a test
    */
   /*
    * @ requires // // A test to be in the database // (\exists Test test &&
    * tests.length > 0) ensures // // test will be put into edit mode //
    * 
    * @
    */
   public void editTest(Test t) {
      System.out.println("in TestDatabase.editTest");
      save();
      //
   }
   /*
    * @ requires // // column && data strings when null will return bad data.
    * undefined behavior. don't do it. // ensures // // Tests returns will be
    * valid for column and data given. Returned list may be of lentgh 0. //
    * 
    * @
    */
   public ArrayList<Test> getTest(String column, String data) {
      final ArrayList<Test> match = new ArrayList<Test>();
      final ArrayList<String> column_names = new ArrayList<String>(Arrays.asList("uniqueId", "state", "testTitle"
                  , "author", "lastUsed", "totalQuestions",
                  "totalPoints", "totalTime", "avgDifficulty", "notes", "course",
                  "gradeType", "password", "startDate", "endDate", "startTime", "endTime",
                  "testType", "testCategory", "testCaregoryNumber"));

      if (column_names.contains(column)) {
         System.out.println("Number of tests in database: "
               + TestDatabase.tests.size());
         for (final Test t : TestDatabase.tests) {
            String val = t.getTestParam(column).toString();
            if (val.equals(data)) {
               match.add(t);
               System.out.println("Match: " + val + " = " + data + " in "
                     + column
                     + ". total matches " + match.size());
            }
         }
      }
      return match;
   }
   /**
    * This method will begin the process of publishing a test
    */
   /*
    * @ requires // // A test to be in the database // (\exists Test test &&
    * tests.length > 0) ensures // // test will be published //
    * 
    * @
    */
   public void publishTest(Test t) {
      System.out.println("in TestDatabase.publishTest");
   }
   /**
    * This method will begin the process of removing a test
    */
   /*
    * @ requires // // A test to be in the database // (\exists Test test &&
    * tests.length > 0) ensures // // test will be removed // (Test test = null)
    * 
    * @
    */
   public void removeTest(Test t) {
      System.out.println("in TestDatabase.removeTest");
      save();
   }
   /**
    * This method will begin the process of taking a test
    */
   /*
    * @ requires // // A test to be in the database // (\exists Test test &&
    * tests.length > 0) ensures // // test will be taken //
    * 
    * @
    */
   public void takeTest(Test t) {
      System.out.println("in TestDatabase.takeTest");
   }
   /*
    * @ ensures // // A weird sample test will be in the database //
    * 
    * @
    *//*
       * public void ZerothTest() { final Test z = new Test(); z.uniqueId =
       * "TESTGENTESTNUMBER0"; z.avgDifficulty = 99; z.lastUsed = "NEVER";
       * z.opened = true; z.testTitle = "ZERO"; z.totalPoints = 9001; z.graded =
       * true; z.published = true; z.avgDifficulty = -3; z.author =
       * "Hex Software"; TestDatabase.tests.add(z); }
       */
  public void save(){
   Charset charset = Charset.forName("US-ASCII");
    String data = tests.toString();
    StringBuilder sb = new StringBuilder(); 
    for (Test t : tests) {
      sb.append(t.toString()); 
      sb.append("\n"); 
    }
    File file = new File("database.txt");
    try (BufferedWriter writer = new BufferedWriter(
        new FileWriter(file.getAbsoluteFile()))) {
      writer.write(data, 0, data.length());
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}
