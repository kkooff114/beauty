package beauty.beautydemo.custview.expandtabview;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.KeyValue;


/**
 * @title: SubView.java
 * @author: Lou Jiwei
 * @Email: kkooff114@gmail.com
 * @version 创建时间：2015-3-11 上午10:48:49
 * @description:扩展筛选单个按钮
 */
public class SubView extends LinearLayout implements ViewBaseAction {

	private Context mContext;
	private GridView mGridView;
	private SubViewButtonListener mSubViewButtonListener;
	private Button cancel_btn, confirm_btn;
	private HashSet<String> mSet = new HashSet<String>();
	private ArrayList<KeyValue> mKeyValus;
	private int mThemeColor = R.color.item_bg_purple;
	private static final int filterBackgroundGray = R.drawable.corners_item_filter_gray;
	private static final int filterBackgroundPurple = R.drawable.corners_item_filter_purple;

	public SubView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = LayoutInflater.from(mContext);
//		(LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.single_listview, this, true);
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		setLayoutParams(params);
		
		mGridView = (GridView) findViewById(R.id.single_list);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		cancel_btn = (Button) findViewById(R.id.cancel_btn);
		confirm_btn = (Button) findViewById(R.id.confirm_btn);
		cancel_btn.setOnClickListener(btnClickListener);
		confirm_btn.setOnClickListener(btnClickListener);

	}

	class Adapter extends BaseAdapter {

		private LayoutInflater adapterInflater;
		private Context adapterContext;

		public Adapter(Context context) {
			adapterContext = context;
			adapterInflater = LayoutInflater.from(adapterContext);
		}

		@Override
		public int getCount() {
			return mKeyValus.size();
		}

		@Override
		public Object getItem(int position) {
			return mKeyValus.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = adapterInflater.inflate(R.layout.item_filter,
						null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.tv_filter);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.textView.setText(mKeyValus.get(position).getValue());
			holder.textView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int currColor = ((TextView) v).getCurrentTextColor();
					if (currColor == getResources().getColor(R.color.white)) {
						mSet.remove(mKeyValus.get(position).getKey());
						((TextView) v).setTextColor(getResources().getColor(R.color.black));
						((TextView) v).setBackground(getResources().getDrawable(filterBackgroundGray));
					}else{
						mSet.add(mKeyValus.get(position).getKey());
						((TextView) v).setTextColor(getResources().getColor(R.color.white));
						((TextView) v).setBackground(getResources().getDrawable(filterBackgroundPurple));
					}
				}
			});

			return convertView;
		}

		class ViewHolder {
			TextView textView;
		}

	}

	private OnClickListener btnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.confirm_btn:

				if (mSubViewButtonListener != null) {
					mSubViewButtonListener.confirm(mSet);
				}
				break;

			case R.id.cancel_btn:

				break;

			default:
				break;
			}
		}
	};

	// 设置确定按钮监听
	public void setSubViewButtonListener(SubViewButtonListener listener) {
		mSubViewButtonListener = listener;
	}

	// 设置数据源
	public void setDate(ArrayList<KeyValue> keyValues) {
		mKeyValus = keyValues;
		
		mGridView.setAdapter(new Adapter(mContext));
	}

	// 设置主题颜色
	public void setThemeColor(int Rcolor) {
		mThemeColor = Rcolor;
	}

	@Override
	public void hide() {

	}

	@Override
	public void show() {

	}

}
