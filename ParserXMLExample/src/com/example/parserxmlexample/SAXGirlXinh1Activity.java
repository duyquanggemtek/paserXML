package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.GirlXinhAdapter;
import com.example.parserxmlexample.ennity.GirlXinh;

public class SAXGirlXinh1Activity extends Activity {

	private ListView mLvGirlXinh;
	private List<GirlXinh> mGirlXinhs;
	private GirlXinh mGirlXinh;
	private GirlXinhAdapter mAdapter;
	private Button mBtnStart;
	private String mTemVal;

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
			InputStream inputStream = getAssets().open("Image.xml");
			InputSource inputSource = new InputSource(inputStream);

			// String content = getContent(inputStream);
			// Log.d("content", "" + content);
			Log.d("log", "" + inputSource);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				@Override
				public void startDocument() throws SAXException {

					mGirlXinhs = new ArrayList<GirlXinh>();
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (localName.equalsIgnoreCase("item")) {
						mGirlXinh = new GirlXinh();
					}
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

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

			};

			parser.parse(inputSource, handler);

			mAdapter = new GirlXinhAdapter(getApplicationContext(), mGirlXinhs);
			mLvGirlXinh.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getContent(InputStream is) {

		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader;
		StringBuilder stringBuilder;
		String line;
		String content = null;

		try {

			inputStreamReader = new InputStreamReader(is);
			bufferedReader = new BufferedReader(inputStreamReader);
			stringBuilder = new StringBuilder();
			line = bufferedReader.readLine();

			while (line != null) {
				stringBuilder.append(line);
			}

			content = stringBuilder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return content;

	}
}
