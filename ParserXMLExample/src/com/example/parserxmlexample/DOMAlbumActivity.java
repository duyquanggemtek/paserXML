package com.example.parserxmlexample;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.AlbumAdapter;
import com.example.parserxmlexample.ennity.Album;

public class DOMAlbumActivity extends Activity {

	private ListView mLvAlbum;
	private Button mBtnStart;
	private Album mAlbum;
	private ArrayList<Album> mAlbums;
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
			InputStream inputStream = getAssets().open("album.xml");
			InputSource inputSource = new InputSource(inputStream);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputSource);
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("item");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					mAlbum = new Album();
					mAlbum.setId(getNode("IdAlbum", element));
					mAlbum.setName(getNode("NameAlbum", element));
					mAlbum.setUrl(getNode("URLImage", element));

					mAlbums.add(mAlbum);

				}
			}

			inputStream.close();
			mAdapter = new AlbumAdapter(getApplicationContext(), mAlbums);
			mLvAlbum.setAdapter(mAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getNode(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}
