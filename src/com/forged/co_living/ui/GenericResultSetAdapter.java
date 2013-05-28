package com.forged.co_living.ui;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.forged.android.co_living.R;

public class GenericResultSetAdapter extends ArrayAdapter<String> {
	
	private ArrayList<String> dataList;
	
	public GenericResultSetAdapter(Context context, ArrayList<String> valList) {
		super(context, R.layout.generic_result_list_layout, R.id.result_item);
		
		dataList = valList;
		
		init();
	}
	
	private void init() {
		
		for(int i = 0; i < dataList.size(); i++) {
			this.add(dataList.get(i));
		}
	}

//	  @Override
//	  public View getView(int position, View convertView, ViewGroup parent) {
//	    LayoutInflater inflater = (LayoutInflater) context
//	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	    View rowView = inflater.inflate(R.layout.generic_result_list_layout, parent, false);
//	    TextView textView = (TextView) rowView.findViewById(R.id.result_item);
//	    ImageView imageView = (ImageView) rowView.findViewById(R.id.iv_icon);
//	    textView.setText(values[position]);
//	    // Change the icon for Windows and iPhone
//	    String s = values[position];
//	    if (s.startsWith("iPhone")) {
//	      imageView.setImageResource(R.drawable.no);
//	    } else {
//	      imageView.setImageResource(R.drawable.ok);
//	    }
//
//	    return rowView;
//	  }
}
