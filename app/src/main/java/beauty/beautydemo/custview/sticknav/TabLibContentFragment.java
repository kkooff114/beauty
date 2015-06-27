package beauty.beautydemo.custview.sticknav;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.tools.Constants;

/**
 * Created by LJW on 15/4/10.
 */
public class TabLibContentFragment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private GridView gv_lib_try, gv_lib_color_board;


    public static TabLibContentFragment newInstance(String title) {
        TabLibContentFragment tabFragment = new TabLibContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_lib_content, container, false);
        gv_lib_try = (GridView) view.findViewById(R.id.gv_lib_try);
        gv_lib_color_board = (GridView) view.findViewById(R.id.gv_lib_color_board);

        ArrayList<String> libTryList = Constants.getLipTryImageList();
        gv_lib_try.setAdapter(new GridViewAdapter(getActivity(),libTryList));

        ArrayList<String> libColorBoard = Constants.getLibColorBoard();
        gv_lib_color_board.setAdapter(new GridViewAdapter(getActivity(), libColorBoard));

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        MaterialViewPagerHelper.registerScrollView(getActivity(),scrollView , null);
    }

    class GridViewAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<String> list;

        public GridViewAdapter(Context context, ArrayList<String> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;

            if (view == null) {

                view = inflater.inflate(R.layout.item_gridview_imageview, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) view.findViewById(R.id.iv_item_gridview);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.iv.setImageDrawable(getActivity().getResources().getDrawable(Integer.valueOf(list.get(i))));

            return view;
        }

        class ViewHolder {
            private ImageView iv;
        }
    }
}
