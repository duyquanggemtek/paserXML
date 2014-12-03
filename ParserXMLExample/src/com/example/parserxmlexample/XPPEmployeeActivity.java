package com.example.parserxmlexample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.EmployeeAdapter;
import com.example.parserxmlexample.ennity.Employee;

public class XPPEmployeeActivity extends Activity {

	private ListView mLvEmployee;
	private Button mBtnStart;
	private Employee mEmployee;
	private ArrayList<Employee> mEmployees;
	private EmployeeAdapter mAdapter;
	private String mTempVal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
	}

	private void initUI() {

		mLvEmployee = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mEmployees = new ArrayList<Employee>();
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				parserXML();
			}
		});

	}

	private void parserXML() {

		try {
			InputStream inputStream = getAssets().open("employees.xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(inputStream, null);

			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {
				String tagName = parser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					if (tagName.equalsIgnoreCase("employee")) {
						mEmployee = new Employee();
					}
					break;
				case XmlPullParser.TEXT:
					mTempVal = parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if (tagName.equalsIgnoreCase("employee")) {
						mEmployees.add(mEmployee);
					} else if (tagName.equalsIgnoreCase("id")) {
						mEmployee.setId(mTempVal);
					} else if (tagName.equalsIgnoreCase("name")) {
						mEmployee.setName(mTempVal);
					} else if (tagName.equalsIgnoreCase("department")) {
						mEmployee.setDepartment(mTempVal);
					} else if (tagName.equalsIgnoreCase("type")) {
						mEmployee.setType(mTempVal);
					} else if (tagName.equalsIgnoreCase("email")) {
						mEmployee.setEmail(mTempVal);
					}
					break;
				}
				event = parser.next();
				inputStream.close();

				mAdapter = new EmployeeAdapter(getApplicationContext(),
						mEmployees);
				mLvEmployee.setAdapter(mAdapter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
