/**
 * AutomaticGeneration class is used to generate tests using given
 * inputs
 * @author Grant Pickett
 * @version 5/31/2014
 */

package testtool.models.testdb;

import java.util.ArrayList;
import java.util.HashMap;

import testtool.models.questiondb.Question;

/**
 * This class manages manual generation of a test.
 */
public class AutomaticGeneration {
	public AutomaticGeneration(TestDatabase td) {
		tdb = td;
	}

	/**
	 * The collection of questions in the test.
	 */
	ArrayList<Question> questions = new ArrayList<Question>();;
	/**
	 * The collection of other data for the test.
	 */
	public HashMap<String, String> settings = new HashMap<String, String>();
	/**
	 * The Test Database class used through the applicaton.
	 */
	TestDatabase tdb;

	/*
	 * This constructor connects the local reference to a testdatabase to the
	 * application's testdatabase. public AutomaticGeneration(TestDatabase td) {
	 * tdb = td; } /** Gets input from instructor and saves it to the settings
	 * variable
	 */
	/*
	 * @ ensures (* That settings are setup with the input information);
	 * 
	 * @
	 */
	public void setParams(String col, String val) {
		settings.put(col, val);
	}

	/**
	 * add takes a question from the add a question dialog, and adds it into the
	 * question list.
	 */
	/*
	 * @ requires (* That question q is a vaild question.);
	 * 
	 * @ ensures (* That a valid question is added to the databank.);
	 */
	void add(Question q) {
		System.out.println("in AutomaticGeneration.add");
		questions.add(q);
	}

	/**
	 * remove takes a collection of Questions qs and removes them from the local
	 * test.
	 * 
	 * @param q
	 *            - question to remove
	 */
	/*
	 * @ requires (* That a one or more questions in the databank are selected.
	 * Also that all questions in qs are valid questions and are in the databank
	 * also.); ensures (* That some valid questions are removed.);
	 * 
	 * @
	 */
	void remove(Question q) {
		System.out.println("in AutomaticGeneration.remove");
		questions.remove(q);
	}

	/**
	 * Lets an instructor finish this step and add the test to the test database
	 * 
	 * @param questions2
	 * @param params
	 */
	/*
	 * @ requires (* There is at least one question in the test); ensures (* The
	 * test is stored in the test database and the user is sent there.);
	 * 
	 * @ enures (* A new test will be added to the testdatabase.
	 */
	public Test generate(HashMap<String, String> params,
			ArrayList<Question> questions2) {
		if(questions2 == null)
			questions2 = new ArrayList<Question>();
		System.out.println("in AutomaticGeneration.generate" +questions2.size());
		Integer totalTime = 0;
		for(Question q : questions2) {
			totalTime += q.time;
		}
		Integer totalPoints = 0;
		for(Question q : questions2) {
			//totalTime += q.points;
		}
		Double avgDifficulty = 0.0;
		for(Question q : questions2) {
			avgDifficulty += q.difficulty;
		}
		avgDifficulty/=questions2.size();

		params.put("totalQuestions", ((Integer)questions2.size()).toString());
		params.put("avgDifficulty", avgDifficulty.toString());
		params.put("totalTime", totalTime.toString());
		params.put("state", "unscheduled");
		params.put("uniqueId", tdb.getIdPos().toString());
		return tdb.createTest(params, questions2);
	}
}
