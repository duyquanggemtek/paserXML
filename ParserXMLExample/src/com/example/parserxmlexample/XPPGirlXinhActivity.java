package com.example.parserxmlexample;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.GirlXinhAdapter;
import com.example.parserxmlexample.ennity.GirlXinh;

public class XPPGirlXinhActivity extends Activity {

	private ListView mLvDomGirlXinh;
	private Button mBtnStart;
	private GirlXinhAdapter mAdapter;
	private ArrayList<GirlXinh> mGirlXinhs;
	private GirlXinh mGirlXinh;
	private String mTemVal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();

	}

	private void initUI() {
		mLvDomGirlXinh = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mGirlXinhs = new ArrayList<GirlXinh>();

		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pullParserXML();
			}
		});

	}

	private void pullParserXML() {

		try {
			InputStream inputStream = getAssets().open("Image.xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			parser.setInput(inputStream, null);
			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {
				String tag = parser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:

					if (tag.equalsIgnoreCase("item")) {
						mGirlXinh = new GirlXinh();
					}

					break;
				case XmlPullParser.TEXT:

					mTemVal = parser.getText();
					break;
				case XmlPullParser.END_TAG:

					if (tag.equalsIgnoreCase("item")) {
						mGirlXinhs.add(mGirlXinh);
					} else if (tag.equalsIgnoreCase("Id")) {
						mGirlXinh.setId(mTemVal);
					} else if (tag.equalsIgnoreCase("Name")) {
						mGirlXinh.setName(mTemVal);
					} else if (tag.equalsIgnoreCase("IdAlbum")) {
						mGirlXinh.setIdAlbum(mTemVal);
					} else if (tag.equalsIgnoreCase("URLImage")) {
						mGirlXinh.setUrlImage(mTemVal);
					}

					break;

				}

				event = parser.next();
			}
			mAdapter = new GirlXinhAdapter(getApplicationContext(), mGirlXinhs);
			mLvDomGirlXinh.setAdapter(mAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
