package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.QuestionBean;
import com.training.dao.ELearningDAO1;

public class ELTC066TestsComplexDataProvider {
	

	@DataProvider(name = "db-inputs") //database
	public Object [][] getDBData() {
	List<QuestionBean> list = new ELearningDAO1().getQuestions(); 
	
	Object[][] result = new Object[list.size()][]; 
	int count = 0; 
	for(QuestionBean temp : list){
		Object[]  obj = new Object[7]; 
		obj[0] = temp.getQuestion(); 
		obj[1] = temp.getOption1(); 
		obj[2] = temp.getOption2();
		obj[3] = temp.getOption3();
		obj[4] = temp.getOption4();
		obj[5] = temp.getAnswerOptn();
		obj[6] = temp.getAnswer();
		
		result[count ++] = obj; 
	}
	
	
	return result;

}

}