package com.goscale;

import java.util.Map;
import java.util.Scanner;

public class StartQuestion {

    private Map<String, Question> questions = null;
    int balance = 100;

    StartQuestion(Map<String, Question> questions)
    {
        this.questions = questions;
    }

    public void startInteraction()
    {
        showQuestion("start");
    }

    private void doAppCheck(String questionName)
    {
        if(questionName.equals("Error")) {
            System.out.println("Please enter correct Mobile Number");
            showQuestion(questions.get(questionName).getPositiveAction());
        }
        else if(questionName.equals("GenerateOTP"))
            System.out.println("Generate OTP Error");
        else if(questionName.equals("Check Balance")) {
            System.out.println("Your balance is 100 Rs.");
            if(balance < 200)
                showQuestion("AskRecharge");
            else
                System.out.println("Press Ok to exit");
        }else if(questionName.equals("No action"))
            System.out.println("Exit");
    }

    private void showQuestion(String questionName)
    {
        if("No action".equals(questionName) || "Error".equals(questionName) ||
                "GenerateOTP".equals(questionName) || "Check Balance".equals(questionName) ){
            doAppCheck(questionName);
        }
        else {
            System.out.println(this.questions.get(questionName).getStatement());
            String type = this.questions.get(questionName).getType();
            if ("Choices".equals(type) && !this.questions.get(questionName).getOptions().isEmpty()) {
                Map<Integer, Option> op = this.questions.get(questionName).getOptions();
                for (Integer i : op.keySet()) {
                    Option o = op.get(i);
                    System.out.println(o.getId() + "  " + o.getStatement());
                }
            }
            String input = getInput(questionName, type);
        }
    }

    private String getInput(String questionName,String type)
    {
        if("Success".equals(questionName) || "Fail".equals(questionName)) {
            return null;
        }

       Scanner sc = new Scanner(System.in);
       String in = sc.next();

       if("Polar".equals(type)) {
           String next = checkPolar(questionName, in);
           if( next != null)
               showQuestion(next);
       }
       else if("TextField".equals(type)) {
           String next = checkTextField(questionName, in);
           if(next != null)
               showQuestion(next);

       }
       else if("Choices".equals(type)) {
           String next = checkOption(questionName, in);
           if( next != null)
               showQuestion(next);
       }
        return null;
    }

    // below methods are to check if input
    private String checkPolar(String questionName,String input)
    {
        if("Yes".equals(input))
            return questions.get(questionName).getPositiveAction();
        else if("No".equals(input))
            return questions.get(questionName).getNegativeAction();
        else
            return null;
    }

    private String checkTextField(String questionName,String input){
        //validation on text field
        try{
            //System.out.println(questionName);
            if("Recharge Amount".equals(questionName)){
                Integer i = Integer.parseInt(input);

                if (i >0 && i < 10000){
                    return questions.get(questionName).getPositiveAction();
                }else
                {
                    return questions.get(questionName).getNegativeAction();
                }
            }
            else if("New User".equals(questionName)){
                String regex = "[7-9][0-9]{9}";
                if(input.matches(regex)){
                    return questions.get(questionName).getPositiveAction();
                }else{
                    return questions.get(questionName).getNegativeAction();
                }
            }
            else if("Validate OTP".equals(questionName)){
                if(String.valueOf(12345).equals(input)){
                    return questions.get(questionName).getPositiveAction();
                }else{
                    return questions.get(questionName).getNegativeAction();
                }
            }
        }
        catch(Exception ex){
            return null;
        }

        return null;
    }

    private String checkOption(String questionName,String input){
        try{
            int size = questions.get(questionName).getOptions().size();
            Integer i = Integer.parseInt(input);

            if(i > 0 && i <= size+1){
                return questions.get(questionName).getOptions().get(i).getPositiveAction();
            }
        }
        catch(Exception ex){
            return null;
        }

        return null;
    }
}
