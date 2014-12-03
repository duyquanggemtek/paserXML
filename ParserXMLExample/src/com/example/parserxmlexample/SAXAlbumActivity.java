package com.example.parserxmlexample;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.AlbumAdapter;
import com.example.parserxmlexample.ennity.Album;
import com.example.parserxmlexample.util.SAXXMLHandlerAlbum;

public class SAXAlbumActivity extends Activity {

	private ListView mLvAlbum;
	private AlbumAdapter mAdapter;
	private List<Album> mAlbums;
	private Button mBtnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
	}

	private void initUI() {

		mLvAlbum = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				XMLParserAlbum();

			}
		});

	}

	private void XMLParserAlbum() {

		try {
			InputStream inputStream = getAssets().open("Album.xml");
			InputSource inputSource = new InputSource(inputStream);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();

			SAXXMLHandlerAlbum handlerAlbum = new SAXXMLHandlerAlbum();
			reader.setContentHandler(handlerAlbum);
			reader.parse(inputSource);

			mAlbums = handlerAlbum.getmAlbums();
			Log.d("abc", "" + mAlbums);
			mAdapter = new AlbumAdapter(getApplicationContext(), mAlbums);
			mLvAlbum.setAdapter(mAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
