package com.rajendraneshwaran.xmlpullparser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLPullParserHandler {

    private List<employee> employees= new ArrayList<employee>();
    private employee employeeo;
    private String text;

    public List<employee> getEmployees() {
        return employees;
    }

    public List<employee> parse(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // create a new instance of employee
                            employeeo = new employee();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // add employee object to list
                            employees.add(employeeo);
                        }else if (tagname.equalsIgnoreCase("id")) {
                            employeeo.setId(Integer.parseInt(text));
                        }  else if (tagname.equalsIgnoreCase("name")) {
                            employeeo.setName(text);
                        } else if (tagname.equalsIgnoreCase("salary")) {
                            employeeo.setSalary(Float.parseFloat(text));
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        return employees;
    }

}
