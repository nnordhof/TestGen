/**
 * The Test class represents a test that lives in the test database
 * It has a list of questions and other needed data
 * @author Grant Pickett, Yuliya Levitskaya
 * @version 5/31/2014
 */

package testtool.models.testdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import testtool.models.questiondb.Question;

public class Test {
	/*
	 * public String uniqueId; public String state; public Collection<Question>
	 * questions; public String testTitle; public String author; public String
	 * lastUsed; public String totalQuestions; public String totalPoints; public
	 * String totalTime; public String avgDifficulty; public String course;
	 */
	public HashMap<String, String> testParams = new HashMap<String, String>();
	public ArrayList<Question> questionList = new ArrayList<Question>();

	public Test(HashMap<String, String> testP, ArrayList<Question> questionL) {
		testParams = testP;
		if (testParams.get("password") == null) {
			testParams.put("password", null);
		}
		questionList = questionL;
	}

	public Test() {

	}

	/*
	 * getTestParam returns the data in a given column, represented by the
	 * string paramerer of this method
	 */
	public String getTestParam(String column) {
		return testParams.get(column);
	}

	/*
	 * setTestParam changes the data in a given column, represented by the first
	 * string paramerer of this method and the second string parameter is the
	 * data
	 */
	public String setTestParam(String column, String data) {
		return testParams.put(column, data);
	}

	/*
	 * getQuestionList returns the questions of this test
	 */
	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("begintest \n");
		for (Entry<String, String> entry : testParams.entrySet()) {
			sb.append("key: " + entry.getKey() + " value: " + entry.getValue());
			sb.append(" \n");
			System.out.println(sb.toString());
		}
		for (Question entry : questionList) {
			sb.append(entry.toString());
			sb.append(" \n");
		}
		sb.append("endtest \n");
		return sb.toString();
	}
}
