package com.example.parserxmlexample.adapter;

import java.util.List;

import com.example.parserxmlexample.R;
import com.example.parserxmlexample.ennity.AndBe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AndBeAdapter extends BaseAdapter {

	private Context mContext;
	private List<AndBe> mAndBes;
	private LayoutInflater mInflater;

	public AndBeAdapter(Context context, List<AndBe> andBes) {
		this.mContext = context;
		this.mAndBes = andBes;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mAndBes.size();
	}

	@Override
	public Object getItem(int position) {
		return mAndBes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		AndBe andBe = mAndBes.get(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_andbe, null);
		}

		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
		TextView tvLink = (TextView) convertView.findViewById(R.id.tvLink);
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);

		tvTitle.setText(andBe.getTitle());
		tvDesc.setText(andBe.getDesc());
		tvLink.setText(andBe.getLink());
		tvDate.setText(andBe.getDate());

		return convertView;
	}

}
