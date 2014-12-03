package com.example.parserxmlexample;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.AlbumAdapter;
import com.example.parserxmlexample.ennity.Album;

public class XPPAlbumActivity extends Activity {

	private ListView mLvAlbum;
	private Button mBtnStart;
	private Album mAlbum;
	private ArrayList<Album> mAlbums;
	private String mTempVal;
	private AlbumAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
	}

	private void initUI() {

		mLvAlbum = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mAlbums = new ArrayList<Album>();
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				parserXML();

			}
		});

	}

	private void parserXML() {

		try {
			InputStream inputStream = getAssets().open("Album.xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			parser.setInput(inputStream, null);

			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {

				String tagName = parser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:

					if (tagName.equalsIgnoreCase("item")) {
						mAlbum = new Album();
					}

					break;
				case XmlPullParser.TEXT:
					mTempVal = parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if (tagName.equalsIgnoreCase("item")) {
						mAlbums.add(mAlbum);
					} else if (tagName.equalsIgnoreCase("IdAlbum")) {
						mAlbum.setId(mTempVal);
					} else if (tagName.equalsIgnoreCase("NameAlbum")) {
						mAlbum.setName(mTempVal);
					} else if (tagName.equalsIgnoreCase("URLImage")) {
						mAlbum.setUrl(mTempVal);
					}
					break;
				}
				event = parser.next();

			}
			Log.d("album", "" + mAlbums);
			mAdapter = new AlbumAdapter(getApplicationContext(), mAlbums);
			mLvAlbum.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
