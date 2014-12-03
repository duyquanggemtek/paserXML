package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.JobAdapter;
import com.example.parserxmlexample.ennity.Job;
import com.example.parserxmlexample.util.SAXXMLHandlerXML;

public class SAXActivity extends Activity {

	private ListView mLvJob;
	private Button mBtnStart;
	private ArrayList<Job> mJobs = null;
	private JobAdapter mAdapter;
	private String XMLData;
	private final String XMLData1 = "<?xml version=\"1.0\"?><login><status>OK</status><jobs><job><id>4</id><companyid>4</companyid><company>Android Example</company><address>Parse XML Android</address><city>Tokio</city><state>Xml Parsing Tutorial</state><zipcode>601301</zipcode><country>Japan</country><telephone>9287893558</telephone><fax>1234567890</fax><date>2012-03-15 12:00:00</date></job><job><id>5</id><companyid>6</companyid><company>Xml Parsing In Java</company><address>B-22</address><city>Cantabill</city><state>XML Parsing Basics</state><zipcode>201301</zipcode><country>America</country><telephone>9287893558</telephone><fax>1234567890</fax><date>2012-05-18 13:00:00</date></job></jobs></login>";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
	}

	public void initUI() {
		mLvJob = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);

		XMLData = getResources().getString(R.string.XML_Data);
		Log.d("xml", "" + XMLData);

		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				parserXML();
			}

		});
	}

	public void parserXML() {
		try {
			// read xml
			BufferedReader bufferedReader = new BufferedReader(
					new StringReader(XMLData1));
			InputSource inputSource = new InputSource(bufferedReader);

			// parse XML

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sp = factory.newSAXParser();
			XMLReader reader = sp.getXMLReader();

			SAXXMLHandlerXML parser = new SAXXMLHandlerXML();
			reader.setContentHandler(parser);
			reader.parse(inputSource);

			mJobs = parser.getJobs();
			Log.d("jobs", "" + mJobs);

			mAdapter = new JobAdapter(this, mJobs);
			mLvJob.setAdapter(mAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
