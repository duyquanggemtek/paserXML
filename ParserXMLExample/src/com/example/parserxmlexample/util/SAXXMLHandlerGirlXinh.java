package com.example.parserxmlexample.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.parserxmlexample.ennity.GirlXinh;

public class SAXXMLHandlerGirlXinh extends DefaultHandler {

	private List<GirlXinh> mGirlXinhs;
	private GirlXinh mGirlXinh;
	private String mTemVal;

	public List<GirlXinh> getmGirlXinhs() {
		return mGirlXinhs;
	}

	@Override
	public void startDocument() throws SAXException {
		mGirlXinhs = new ArrayList<GirlXinh>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equalsIgnoreCase("item")) {
			mGirlXinh = new GirlXinh();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equalsIgnoreCase("item")) {
			mGirlXinhs.add(mGirlXinh);
		} else if (localName.equalsIgnoreCase("Id")) {
			mGirlXinh.setId(mTemVal);
		} else if (localName.equalsIgnoreCase("Name")) {
			mGirlXinh.setName(mTemVal);
		} else if (localName.equalsIgnoreCase("IdAlbum")) {
			mGirlXinh.setIdAlbum(mTemVal);
		} else if (localName.equalsIgnoreCase("URLImage")) {
			mGirlXinh.setUrlImage(mTemVal);
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		mTemVal = new String(ch, start, length);
	}

}
