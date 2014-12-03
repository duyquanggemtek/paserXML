package com.example.parserxmlexample.adapter;

import java.util.List;

import com.androidquery.AQuery;
import com.example.parserxmlexample.R;
import com.example.parserxmlexample.ennity.Album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {

	private Context mContext;
	private List<Album> mAlbums;
	private LayoutInflater mInflater;
	private AQuery aQuery;

	public AlbumAdapter(Context context, List<Album> albums) {
		this.mContext = context;
		this.mAlbums = albums;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mAlbums.size();
	}

	@Override
	public Object getItem(int position) {
		return mAlbums.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		Album album = mAlbums.get(position);
		aQuery = new AQuery(mContext);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_album, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvId.setText(album.getId());
		holder.tvName.setText(album.getName());
		aQuery.id(holder.ivUrl).image(album.getUrl());

		return convertView;
	}

	public class ViewHolder {

		TextView tvId;
		TextView tvName;
		ImageView ivUrl;

		public ViewHolder(View v) {

			tvId = (TextView) v.findViewById(R.id.tvId);
			tvName = (TextView) v.findViewById(R.id.tvName);
			ivUrl = (ImageView) v.findViewById(R.id.ivUrl);
		}
	}

}
