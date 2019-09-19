package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.LoginBean;
import com.training.bean.QuestionBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

public class ELearningDAO1 {
	
Properties properties; 
	
	public ELearningDAO1() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<QuestionBean> getQuestions(){
		String sql = properties.getProperty("get.questn"); 
		
		GetConnection gc  = new GetConnection(); 
		List<QuestionBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<QuestionBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				QuestionBean temp = new QuestionBean(); 
				temp.setQuestion(gc.rs1.getString(1));
				temp.setOption1(gc.rs1.getString(2));
				temp.setOption2(gc.rs1.getString(3));
				temp.setOption3(gc.rs1.getString(4));
				temp.setOption4(gc.rs1.getString(5));
				temp.setAnswerOptn(gc.rs1.getString(6));
				temp.setAnswer(gc.rs1.getString(7));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new ELearningDAO1().getQuestions().forEach(System.out :: println);
	}
	

}
