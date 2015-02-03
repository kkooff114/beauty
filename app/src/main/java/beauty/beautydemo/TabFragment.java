package beauty.beautydemo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.view.LayoutInflater;



/**
 * Created by chenqiming on 2/2/15.
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class TabFragment extends Fragment implements TabHost.OnTabChangeListener{
    private View root;
    private TabHost tabHost;
    private int currentTab;
    private LayoutInflater inflater;

    public TabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        root = inflater.inflate(R.layout.fragment_main, null);
        tabHost = (TabHost) root.findViewById(android.R.id.tabhost);
        setupTabs();
        updateTab("tab1", R.id.tab1);
        return root;
    }

    private void setupTabs() {
        tabHost.setup();
        tabHost.addTab(newTab("tab1", R.drawable.ic_action_locate, R.id.tab1));
        tabHost.addTab(newTab("tab2", R.drawable.ic_action_edit, R.id.tab2));
        tabHost.addTab(newTab("tab3", R.drawable.ic_action_search, R.id.tab3));
        tabHost.setOnTabChangedListener(this);
        setTabColor(tabHost);
    }

    private TabHost.TabSpec newTab(String tag, int labelId, int tabContentId) {
        Log.i("Beauty", "tabid " + 0);
        View indicator = inflater.inflate(R.layout.tab, (ViewGroup) root.findViewById(android.R.id.tabs), false);
        ((ImageView) indicator.findViewById(R.id.tab)).setImageResource(labelId);
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(indicator);
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }

    @Override
    public void onTabChanged(String tabId) {
        if ("tab1".equals(tabId)) {
            updateTab(tabId, R.id.tab1);
            currentTab = 1;
        }
        if ("tab2".equals(tabId)) {
            updateTab(tabId, R.id.tab2);
            currentTab = 2;
        }
        if ("tab3".equals(tabId)) {
            updateTab(tabId, R.id.tab3);
            currentTab = 3;
        }
        setTabColor(tabHost);
    }

    private void updateTab(String tabId, int placeholder) {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag(tabId) == null && "tab1".equals(tabId)) {
            fm.beginTransaction().add(placeholder, new GetColorFragment()).commit();
        }
        if (fm.findFragmentByTag(tabId) == null && "tab2".equals(tabId)) {
            fm.beginTransaction().add(placeholder, new GetFaceFragment()).commit();
        }
        if (fm.findFragmentByTag(tabId) == null && "tab3".equals(tabId)) {
            fm.beginTransaction().add(placeholder, new GetProductFragment()).commit();
        }
    }

    public static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#555555")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#AAAAAA")); // selected
    }
}