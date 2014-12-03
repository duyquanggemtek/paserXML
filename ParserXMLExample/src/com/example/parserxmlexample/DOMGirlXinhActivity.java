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

import com.example.parserxmlexample.adapter.GirlXinhAdapter;
import com.example.parserxmlexample.ennity.GirlXinh;

public class DOMGirlXinhActivity extends Activity {

	private ListView mLvGirlXinh;
	private Button mBtnStart;
	private GirlXinhAdapter mAdapter;
	private GirlXinh mGirlXinh;
	private ArrayList<GirlXinh> mGirlXinhs;

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

		try {
			InputStream inputStream = getAssets().open("Image.xml");
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
					mGirlXinh = new GirlXinh();

					mGirlXinh.setId(getNode("Id", element));
					mGirlXinh.setName(getNode("Name", element));
					mGirlXinh.setIdAlbum(getNode("IdAlbum", element));
					mGirlXinh.setUrlImage(getNode("URLImage", element));

					mGirlXinhs.add(mGirlXinh);

				}

			}
			mAdapter = new GirlXinhAdapter(getApplicationContext(), mGirlXinhs);
			mLvGirlXinh.setAdapter(mAdapter);

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
