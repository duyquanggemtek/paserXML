package com.example.parserxmlexample.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.parserxmlexample.R;
import com.example.parserxmlexample.ennity.GirlXinh;

public class GirlXinhAdapter extends BaseAdapter {

	private Context mContext;
	private List<GirlXinh> mGirlXinhs;
	private LayoutInflater mInflater;
	private AQuery mAQuery;

	public GirlXinhAdapter(Context context, List<GirlXinh> girlXinhs) {

		this.mContext = context;
		this.mGirlXinhs = girlXinhs;
		this.mInflater = LayoutInflater.from(mContext);
		mAQuery = new AQuery(mContext);
	}

	@Override
	public int getCount() {
		return mGirlXinhs.size();
	}

	@Override
	public Object getItem(int position) {
		return mGirlXinhs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		GirlXinh girlXinh = mGirlXinhs.get(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_image, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvId.setText(girlXinh.getId());
		holder.tvName.setText(girlXinh.getName());
		holder.tvIdAlbum.setText(girlXinh.getIdAlbum());
		mAQuery.id(holder.ivUrlImage).image(girlXinh.getUrlImage());

		return convertView;
	}

	class ViewHolder {

		TextView tvId;
		TextView tvName;
		TextView tvIdAlbum;
		ImageView ivUrlImage;

		public ViewHolder(View v) {

			tvId = (TextView) v.findViewById(R.id.tvId);
			tvName = (TextView) v.findViewById(R.id.tvName);
			tvIdAlbum = (TextView) v.findViewById(R.id.tvIdAlbum);
			ivUrlImage = (ImageView) v.findViewById(R.id.ivUrl);
		}
	}

}
