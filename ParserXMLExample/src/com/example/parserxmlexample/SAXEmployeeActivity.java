package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.EmployeeAdapter;
import com.example.parserxmlexample.ennity.Employee;
import com.example.parserxmlexample.util.SAXXMLHandlerEmployee;

public class SAXEmployeeActivity extends Activity {

	private ListView mLvEmployee;
	private List<Employee> mEmployees;
	private EmployeeAdapter mAdapter;
	private Button mBtnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sax_employee);

		initUI();

	}

	private void initUI() {

		mLvEmployee = (ListView) findViewById(R.id.lvEmployee);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(getAssets().open(
									"employees.xml")));
					InputSource inputSource = new InputSource(bufferedReader);

					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser parser = factory.newSAXParser();
					XMLReader reader = parser.getXMLReader();

					SAXXMLHandlerEmployee handler = new SAXXMLHandlerEmployee();
					reader.setContentHandler(handler);
					reader.parse(inputSource);

					mEmployees = handler.getmEmployees();

					mAdapter = new EmployeeAdapter(getApplicationContext(),
							mEmployees);
					mLvEmployee.setAdapter(mAdapter);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
