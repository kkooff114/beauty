package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import beauty.beautydemo.R;
import beauty.beautydemo.fragment.ColorFragment;
import beauty.beautydemo.fragment.IdeaFragment;
import beauty.beautydemo.fragment.PropertyFragment;
import beauty.beautydemo.fragment.TestLibFragment;

/**
 * Created by LJW on 15/3/17.
 * 主界面
 */
public class MainActivity extends FragmentActivity {

    private Fragment mContent;
    private RadioGroup main_tab_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_host);

        main_tab_group = (RadioGroup) findViewById(R.id.main_tab_group);
        switchContent(new ColorFragment());

        main_tab_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_tab_color:
                        switchContent(new ColorFragment());
                        break;

                    case R.id.main_tab_idea:
                        switchContent(new IdeaFragment());
                        break;

                    case R.id.main_tab_testlib:
                        switchContent(new TestLibFragment());
                        break;

                    case R.id.main_tab_property:
                        switchContent(new PropertyFragment());
                        break;
                }
            }
        });
    }




    /**
     * 底部菜单更新界面
     * @param fragment
     */
    private void switchContent(Fragment fragment) {

        mContent = fragment;

        getSupportFragmentManager().beginTransaction().replace(android.R.id.tabcontent, fragment).commit();

    }
}
