package com.example.parserxmlexample.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.parserxmlexample.ennity.Employee;

public class SAXXMLHandlerEmployee extends DefaultHandler {

	private List<Employee> mEmployees;
	private Employee mEmployee;
	private String tempVal;

	public SAXXMLHandlerEmployee() {
		mEmployees = new ArrayList<Employee>();

	}

	public List<Employee> getmEmployees() {
		return mEmployees;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("employee")) {
			mEmployee = new Employee();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		tempVal = new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("employee")) {
			mEmployees.add(mEmployee);
		} else if (qName.equalsIgnoreCase("id")) {
			mEmployee.setId(tempVal);
		} else if (qName.equalsIgnoreCase("name")) {
			mEmployee.setName(tempVal);
		} else if (qName.equalsIgnoreCase("department")) {
			mEmployee.setDepartment(tempVal);
		} else if (qName.equalsIgnoreCase("type")) {
			mEmployee.setType(tempVal);
		} else if (qName.equalsIgnoreCase("email")) {
			mEmployee.setEmail(tempVal);
		}

	}

}
