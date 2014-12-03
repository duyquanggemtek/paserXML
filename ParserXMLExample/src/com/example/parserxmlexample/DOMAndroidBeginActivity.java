package com.example.parserxmlexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parserxmlexample.adapter.AndBeAdapter;
import com.example.parserxmlexample.ennity.AndBe;

public class DOMAndroidBeginActivity extends Activity {

	private Button mBtnStart;
	private ListView mLvAndbe;
	private ProgressDialog mDialog;
	private ArrayList<AndBe> mAndBes;
	private NodeList mNodeList;
	private AndBeAdapter mAdapter;

	private final String mURL = "http://www.androidbegin.com/tutorial/XMLParseTutorial.xml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);

		initUI();
		new Thread(new Runnable() {

			@Override
			public void run() {
				String xml = getXmlFromUrl(mURL);
				Log.d("123", xml);
			}
		}).start();

	}

	public String getXmlFromUrl(String link) {
		String xml = null;

		try {
			// defaultHttpClient
			// DefaultHttpClient httpClient = new DefaultHttpClient();
			// HttpPost httpPost = new HttpPost(url);
			//
			// HttpResponse httpResponse = httpClient.execute(httpPost);
			// HttpEntity httpEntity = httpResponse.getEntity();
			// InputStream inputStream = httpEntity.getContent();

			URL url = new URL(link);
			URLConnection urlConnection = url.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			StringBuilder stringBuilder = new StringBuilder();
			String line = "";
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
			bufferedReader.close();
			xml = stringBuilder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return XML
		return xml;
	}

	private void initUI() {
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mLvAndbe = (ListView) findViewById(R.id.lvJob);
		mDialog = new ProgressDialog(this);
		mAndBes = new ArrayList<AndBe>();

		mBtnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new ParserXML().execute(mURL);

			}
		});
	}

	private class ParserXML extends AsyncTask<String, Void, ArrayList<AndBe>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mDialog.show();
		}

		@Override
		protected ArrayList<AndBe> doInBackground(String... params) {

			try {
				URL url = new URL(params[0]);
				URLConnection urlConnection = url.openConnection();
				InputStream inputStream = urlConnection.getInputStream();

				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document document = builder.parse(inputStream);
				document.getDocumentElement().normalize();
				Log.d("document", "" + document);

				mNodeList = document.getElementsByTagName("item");

				for (int i = 0; i < mNodeList.getLength(); i++) {

					Node node = mNodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						AndBe andBe = new AndBe();
						Element element = (Element) node;

						andBe.setTitle(getNode("title", element));
						andBe.setDesc(getNode("description", element));
						andBe.setLink(getNode("link", element));
						andBe.setDate(getNode("date", element));

						mAndBes.add(andBe);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return mAndBes;
		}

		@Override
		protected void onPostExecute(ArrayList<AndBe> andBes) {
			super.onPostExecute(andBes);

			mAdapter = new AndBeAdapter(getApplicationContext(), mAndBes);
			mLvAndbe.setAdapter(mAdapter);
			mDialog.dismiss();

		}

		private String getNode(String sTag, Element element) {

			NodeList nodeList = element.getElementsByTagName(sTag).item(0)
					.getChildNodes();
			Node node = nodeList.item(0);

			return node.getNodeValue();

		}

	}

}
