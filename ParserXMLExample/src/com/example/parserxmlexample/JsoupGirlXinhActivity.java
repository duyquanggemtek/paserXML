package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.parserxmlexample.adapter.GirlXinhAdapter;
import com.example.parserxmlexample.ennity.GirlXinh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class JsoupGirlXinhActivity extends Activity {

	private ListView mLvGirlXinh;
	private Button mBtnStart;
	private GirlXinh mGirlXinh;
	private ArrayList<GirlXinh> mGirlXinhs;
	private GirlXinhAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
	}

	private void initUI() {
		mLvGirlXinh = (ListView) findViewById(R.id.lvJob);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mGirlXinhs = new ArrayList<GirlXinh>();
		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				parserXML();
			}
		});
	}

	private void parserXML() {

		Document document = Jsoup.parse(getContent("girlxinh.xml"));
		Elements items = document.select("item");
		for (int i = 0; i < items.size(); i++) {

			mGirlXinh = new GirlXinh();
			Element item = items.get(i);
			mGirlXinh.setId(item.select("Id").get(0).text());
			mGirlXinh.setName(item.select("Name").get(0).text());
			mGirlXinh.setIdAlbum(item.select("IdAlbum").get(0).text());
			mGirlXinh.setUrlImage(item.select("URLImage").get(0).text());

			mGirlXinhs.add(mGirlXinh);
		}

		mAdapter = new GirlXinhAdapter(getApplicationContext(), mGirlXinhs);
		mLvGirlXinh.setAdapter(mAdapter);

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
