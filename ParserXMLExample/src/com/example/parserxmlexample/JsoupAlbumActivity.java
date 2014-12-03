package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.AlbumAdapter;
import com.example.parserxmlexample.ennity.Album;

public class JsoupAlbumActivity extends Activity {

	private ListView mLvAlbum;
	private Button mBtnStart;
	private Album mAlbum;
	private ArrayList<Album> mAlbums;
	private AlbumAdapter mAdapter;
	private String mHtml = "";

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
		mHtml = getContent("album.xml");
		Log.d("123", mHtml);

		Document document = Jsoup.parse(mHtml);
		Elements items = document.select("item");
		for (int i = 0; i < items.size(); i++) {

			mAlbum = new Album();
			Element item = items.get(i);

			String idAlbum = item.select("IdAlbum").get(0).text();
			String nameAlbum = item.select("NameAlbum").get(0).text();
			String urlImage = item.select("URLImage").get(0).text();

			mAlbum.setId(idAlbum);
			mAlbum.setName(nameAlbum);
			mAlbum.setUrl(urlImage);

			mAlbums.add(mAlbum);

		}

		mAdapter = new AlbumAdapter(getApplicationContext(), mAlbums);
		mLvAlbum.setAdapter(mAdapter);
	}

	private String getContent(String link) {

		String content = "";
		String line = "";
		StringBuilder stringBuilder = new StringBuilder();

		try {
			InputStream inputStream = getAssets().open(link);
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = bufferedReader.readLine();
			}
			inputStream.close();
			content = stringBuilder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;

	}
}
