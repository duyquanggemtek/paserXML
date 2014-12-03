package com.example.parserxmlexample.adapter;

import java.util.List;

import com.example.parserxmlexample.R;
import com.example.parserxmlexample.ennity.Employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EmployeeAdapter extends BaseAdapter {

	private Context mContext;
	private List<Employee> mEmployees;
	private LayoutInflater mInflater;

	public EmployeeAdapter(Context context, List<Employee> employees) {
		this.mContext = context;
		this.mEmployees = employees;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mEmployees.size();
	}

	@Override
	public Object getItem(int position) {
		return mEmployees.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Employee employee = mEmployees.get(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_employee, null);
		}

		TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
		TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
		TextView tvDepartment = (TextView) convertView
				.findViewById(R.id.tvDepartment);
		TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
		TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);

		if (mEmployees != null) {
			tvId.setText(employee.getId());
			tvName.setText(employee.getName());
			tvDepartment.setText(employee.getDepartment());
			tvType.setText(employee.getType());
			tvEmail.setText(employee.getEmail());
		}

		return convertView;
	}

}
