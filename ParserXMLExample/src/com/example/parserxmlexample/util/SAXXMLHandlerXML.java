package com.example.parserxmlexample.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.parserxmlexample.ennity.Job;

public class SAXXMLHandlerXML extends DefaultHandler {

	public ArrayList<Job> jobs = null;
	Job job = null;
	StringBuilder stringBuilder;
	String tembString = "";

	public SAXXMLHandlerXML() {
		jobs = new ArrayList<Job>();
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		stringBuilder = new StringBuilder();

		if (localName.equals("job")) {
			job = new Job();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);

		if (localName.equals("job")) {
			jobs.add(job);
		} else if (localName.equalsIgnoreCase("id")) {
			job.setId(tembString);
		} else if (localName.equalsIgnoreCase("companyid")) {
			job.setCompanyId(tembString);
		} else if (localName.equalsIgnoreCase("company")) {
			job.setCompany(tembString);
		} else if (localName.equalsIgnoreCase("address")) {
			job.setAddress(tembString);
		} else if (localName.equalsIgnoreCase("city")) {
			job.setCity(tembString);
		} else if (localName.equalsIgnoreCase("state")) {
			job.setState(tembString);
		} else if (localName.equalsIgnoreCase("zipcode")) {
			job.setZipCode(tembString);
		} else if (localName.equalsIgnoreCase("country")) {
			job.setCountry(tembString);
		} else if (localName.equalsIgnoreCase("telephone")) {
			job.setTelephone(tembString);
		} else if (localName.equalsIgnoreCase("date")) {
			job.setDate(tembString);
		} else if (localName.equalsIgnoreCase("fax")) {
			job.setFax(tembString);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);

		tembString = new String(ch, start, length);
		stringBuilder.append(tembString);
	}
}
