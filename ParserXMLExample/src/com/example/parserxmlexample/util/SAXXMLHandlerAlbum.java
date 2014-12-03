package com.example.parserxmlexample.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.parserxmlexample.ennity.Album;

public class SAXXMLHandlerAlbum extends DefaultHandler {

	private List<Album> mAlbums;
	private Album mAlbum;
	private String mTempString;

	public SAXXMLHandlerAlbum() {
		mAlbums = new ArrayList<Album>();

	}

	public List<Album> getmAlbums() {
		return mAlbums;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equalsIgnoreCase("item")) {
			mAlbum = new Album();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.equalsIgnoreCase("item")) {
			mAlbums.add(mAlbum);
		} else if (localName.equalsIgnoreCase("IdAlbum")) {
			mAlbum.setId(mTempString);
		} else if (localName.equalsIgnoreCase("NameAlbum")) {
			mAlbum.setName(mTempString);
		} else if (localName.equalsIgnoreCase("URLImage")) {
			mAlbum.setUrl(mTempString);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		mTempString = new String(ch, start, length);
	}

}
