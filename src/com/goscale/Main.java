package com.goscale;

import java.io.File;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String path = "resources/config.xml";
        LoadXML obj = new LoadXML();


        File f = new File(path);
       if(f.isFile() && f.getName().endsWith("xml")) {
           Map<String, Question> listOfQuestions = obj.parseXML(f);
           StartQuestion st = new StartQuestion(listOfQuestions);
            //After parsing stat user interaction
           st.startInteraction();
        }
    }
}
