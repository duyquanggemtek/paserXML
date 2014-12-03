package com.example.parserxmlexample;

import java.io.BufferedInputStream;
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

import com.example.parserxmlexample.adapter.GirlXinhAdapter;
import com.example.parserxmlexample.ennity.GirlXinh;
import com.example.parserxmlexample.util.SAXXMLHandlerGirlXinh;

public class SAXGirlXinhActivity extends Activity {

	private ListView mLvGirlXinh;
	private List<GirlXinh> mGirlXinhs;
	private GirlXinhAdapter mAdapter;
	private Button mBtnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();

	}

	private void initUI() {

		mLvGirlXinh = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				parserXMLGirlXinh();

			}
		});

	}

	private void parserXMLGirlXinh() {

		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					getAssets().open("Image.xml"));
			InputSource inputSource = new InputSource(bufferedInputStream);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			SAXXMLHandlerGirlXinh handler = new SAXXMLHandlerGirlXinh();
			parser.parse(inputSource, handler);

			mGirlXinhs = handler.getmGirlXinhs();
			mAdapter = new GirlXinhAdapter(getApplicationContext(), mGirlXinhs);
			mLvGirlXinh.setAdapter(mAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
