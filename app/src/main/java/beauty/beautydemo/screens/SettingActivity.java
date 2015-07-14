package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BaseNoteActivity;
import beauty.beautydemo.fragment.SettingFragment;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/5/24.
 */
public class SettingActivity extends BaseNoteActivity{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_setting;
    }

    @Override
    protected List<Object> getModules() {
//        return Arrays.<Object>asList(new DataModule());
        return new ArrayList<Object>();
    }

    @Override
    protected void initToolbar(){
        super.initToolbar(toolbar);
        toolbar.setTitle(R.string.setting);
    }

    private void init(){
        SettingFragment settingFragment = SettingFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.fragment_content, settingFragment).commit();
    }

}
