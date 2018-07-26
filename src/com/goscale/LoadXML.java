package com.goscale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadXML {

    public Map<String, Question> parseXML(File file)
    {
        Map<String, Question> listOfQuestions = new HashMap<String, Question>();
        try {
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = dBuilder.parse(file);

                NodeList nodeList = doc.getElementsByTagName("Questions");

                NodeList questionNodeList = nodeList.item(0).getChildNodes();

                for (int i = 0; i < questionNodeList.getLength(); i++) {
                    Node currentNode = questionNodeList.item(i);
                    if (currentNode.getNodeName().equals("Question")){
                        Element leafElement = (Element) currentNode;
                        Question ques = createQuestion(leafElement);
                        listOfQuestions.put(leafElement.getAttribute("name"),ques);
                    }
                }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        catch (ParserConfigurationException ex){
            System.out.println(ex.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return listOfQuestions;
    }

    private Question createQuestion(Element leafElement) {
        Question ques = null;
        Option op = null;
        Integer id =  Integer.parseInt(leafElement.getElementsByTagName("Id").item(0).getTextContent());
        String statement = leafElement.getElementsByTagName("Statement").item(0).getTextContent();
        String type = leafElement.getElementsByTagName("Type").item(0).getTextContent();

        if("Polar".equals(type)  || "TextField".equals(type)){
            String positiveAction = leafElement.getElementsByTagName("PositiveAction").item(0).getTextContent();
            String negativeAction = leafElement.getElementsByTagName("NegativeAction").item(0).getTextContent();
            ques = new Question(id, statement, type, positiveAction, negativeAction);
        }
        else if("Choices".equals(type)){
            NodeList currentNode1 = leafElement.getElementsByTagName("Options");
            NodeList tempList = currentNode1.item(0).getChildNodes();

            ques = new Question(id, statement , type, null , null);
            for (int i = 0; i < tempList.getLength(); i++) {
                Node currentNode = tempList.item(i);
                if (currentNode.getNodeName().equals("Option")){
                    Element el = (Element) currentNode;
                    Integer id1 =Integer.parseInt(el.getElementsByTagName("Id").item(0).getTextContent());
                    String statement1 = el.getElementsByTagName("Statement").item(0).getTextContent();
                    String positiveAction = el.getElementsByTagName("PositiveAction").item(0).getTextContent();

                    op = new Option(id1, statement1,positiveAction);

                    Map<Integer ,Option> options = ques.getOptions();

                    if(options == null) {
                        options = new HashMap<Integer ,Option>();
                        options.put(id1 , op);
                    }
                    else {
                        options.put(id1 , op);
                    }
                    ques.setOptions(options);
                }
            }
        }
        return ques;
    }

    public void printQuestions(Map<String, Question> questions)
    {
        for (String question: questions.keySet()
             ) {
            Question q = questions.get(question);

            if(q != null)
                System.out.println("questions  " + question + "   Type  " + questions.get(question).getType() + "   Question   "  + questions.get(question).getStatement());
        }
    }
}
