package com.example.parserxmlexample.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parserxmlexample.R;
import com.example.parserxmlexample.ennity.Job;

public class JobAdapter extends BaseAdapter {

	private Context mContext;
	private List<Job> mJobs;
	private LayoutInflater mInflater;

	public JobAdapter(Context context, List<Job> jobs) {

		this.mContext = context;
		this.mJobs = jobs;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mJobs.size();
	}

	@Override
	public Object getItem(int position) {
		return mJobs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Job job = (Job) getItem(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_job, null);

		}

		TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
		TextView tvCompanyId = (TextView) convertView
				.findViewById(R.id.tvCompanyId);
		TextView tvCompany = (TextView) convertView
				.findViewById(R.id.tvCompany);
		TextView tvAddress = (TextView) convertView
				.findViewById(R.id.tvAndress);
		TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
		TextView tvState = (TextView) convertView.findViewById(R.id.tvState);
		TextView tvZipCode = (TextView) convertView
				.findViewById(R.id.tvZipCode);
		TextView tvCountry = (TextView) convertView
				.findViewById(R.id.tvCountry);
		TextView tvTelephone = (TextView) convertView
				.findViewById(R.id.tvTelephone);
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
		TextView tvFax = (TextView) convertView.findViewById(R.id.tvFax);

		if (mJobs != null) {
			tvId.setText(job.getId());
			tvCompanyId.setText(job.getCompanyId());
			tvCompany.setText(job.getCompany());
			tvAddress.setText(job.getAddress());
			tvCity.setText(job.getCity());
			tvState.setText(job.getState());
			tvZipCode.setText(job.getZipCode());
			tvCountry.setText(job.getCountry());
			tvTelephone.setText(job.getTelephone());
			tvDate.setText(job.getDate());
			tvFax.setText(job.getFax());

		}
		return convertView;
	}
}
