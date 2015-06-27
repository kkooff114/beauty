package beauty.beautydemo.screens.materialmenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.fragment.HomeFragment;
import beauty.beautydemo.fragment.MomentsFragment;
import beauty.beautydemo.fragment.PropertyMaterialFragment;
import beauty.beautydemo.fragment.TestLibFragment;
import beauty.beautydemo.screens.PropertyMaterialActivity;
import beauty.beautydemo.tools.ScreenTools;


public class SimpleHeaderDrawerActivity extends BeautyBaseActivity {
    private static final int PROFILE_SETTING = 1;
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    private Fragment mContent;//现在的content
    private String mContentTag = "";
    private String mPreContentTag = "";
    private FrameLayout mframeContainer;

    private MenuItem inboxMenuItem;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private boolean pendingIntroAnimation;
    private Toolbar toolbar;
    private View mToolShadow;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dark_toolbar);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
//            feedAdapter.updateItems(false);
        }

        mframeContainer = (FrameLayout) findViewById(R.id.frame_container);

        //Remove line to test RTL support
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolShadow = (View) findViewById(R.id.tool_shadow);
        setSupportActionBar(toolbar);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");
        final IProfile profile2 = new ProfileDrawerItem().withName("Bernat Borras").withEmail("alorma@github.com").withIcon(Uri.parse("https://avatars3.githubusercontent.com/u/887462?v=3&s=460"));
        final IProfile profile3 = new ProfileDrawerItem().withName("Max Muster").withEmail("max.mustermann@gmail.com").withIcon(getResources().getDrawable(R.drawable.question0605));
        final IProfile profile4 = new ProfileDrawerItem().withName("Felix House").withEmail("felix.house@gmail.com").withIcon(getResources().getDrawable(R.drawable.question0604));
        final IProfile profile5 = new ProfileDrawerItem().withName("Mr. X").withEmail("mister.x.super@gmail.com").withIcon(getResources().getDrawable(R.drawable.question0603)).withIdentifier(4);
        final IProfile profile6 = new ProfileDrawerItem().withName("Batman").withEmail("batman@gmail.com").withIcon(getResources().getDrawable(R.drawable.question0602));

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        profile2,
                        profile3,
                        profile4,
                        profile5,
                        profile6,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(final View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman").withEmail("batman@gmail.com").withIcon(getResources().getDrawable(R.drawable.question0601));
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        startActivityRelLocation(view, SimpleHeaderDrawerActivity.this, PropertyMaterialActivity.class);

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.tab_home)
                                .withIconColor(getResources().getColor(R.color.md_blue_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(FontAwesome.Icon.faw_home)
                                .withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_color)
                                .withIconColor(getResources().getColor(R.color.md_cyan_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_face)
                                .withIdentifier(2).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_idea)
                                .withIconColor(getResources().getColor(R.color.md_purple_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_format_list_bulleted)
                                .withIdentifier(3).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_testlib)
                                .withIconColor(getResources().getColor(R.color.md_deep_orange_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_blur_on)
                                .withIdentifier(4).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_person)
                                .withIconColor(getResources().getColor(R.color.md_green_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_person)
                                .withIdentifier(5).withCheckable(false),

                        // 发现
                        new SectionDrawerItem().withName(R.string.tab_selection_discover),
                        new PrimaryDrawerItem().withName(R.string.tab_moments)
                                .withIconColor(getResources().getColor(R.color.md_red_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_camera)
                                .withIdentifier(6).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_peoper_nearby)
                                .withIconColor(getResources().getColor(R.color.md_deep_purple_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_hdr_strong)
                                .withIdentifier(7).withCheckable(false),

                        // 笔记
                        new SectionDrawerItem().withName(R.string.tab_selection_note),
                        new PrimaryDrawerItem().withName(R.string.tab_note)
                                .withIconColor(getResources().getColor(R.color.md_light_blue_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_border_color)
                                .withIdentifier(8).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_makeuplib)
                                .withIconColor(getResources().getColor(R.color.md_orange_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_style)
                                .withIdentifier(9).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_property)
                                .withIconColor(getResources().getColor(R.color.md_lime_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIcon(GoogleMaterial.Icon.gmd_grid_on)
                                .withIdentifier(10).withCheckable(false),

                        //设置
                        new SectionDrawerItem().withName(""),
                        new PrimaryDrawerItem().withName(R.string.tab_theme)
                                .withIconColor(getResources().getColor(R.color.md_lime_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIdentifier(11).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.tab_setting)
                                .withIconColor(getResources().getColor(R.color.md_lime_400))
                                .withTextColor(getResources().getColor(R.color.drawlayout_item_text_color))
                                .withSelectedIconColor(getResources().getColor(R.color.white)).withSelectedTextColor(getResources().getColor(R.color.white))
                                .withSelectedColor(getResources().getColor(R.color.theme_color))
                                .withIdentifier(12).withCheckable(false)


                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {

                            updateContent(drawerItem.getIdentifier(), view);

                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        //only set the active selection or active profile if we do not recreate the activity
//        if (savedInstanceState == null) {
        // set the selection to the item with the identifier 10

        lanuchContentFirst();
        //set the active profile
        headerResult.setActiveProfile(profile3);
//        }
    }

    private void lanuchContentFirst() {

        int menuId = getIntent().getIntExtra(MENUID, 1);

        if (ACTION_SHOW_LOADING_ITEM.equals(getIntent().getAction())) {
            updateContent(6, null);
            return;
        }

        updateContent(menuId, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        lanuchContentFirst();
    }

    private void updateContent(int index, View view) {

        switch (index) {
            case 1:
                switchContent(HomeFragment.newInstance("主页"), view, "1");
                break;

            case 4:
                switchContent(TestLibFragment.newInstance("测试库"), view, "4");
                break;

            case 5:
                switchContent(PropertyMaterialFragment.getInstance("个人"), view, "5");
                break;

            case 6:

                MomentsFragment mf = MomentsFragment.getInstance("朋友圈");
                if (ACTION_SHOW_LOADING_ITEM.equals(getIntent().getAction())) {
                    mf.isPublic = true;
                }
                switchContent(mf, view, "6");
                break;
        }

        result.setSelectionByIdentifier(index, false);

    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);

        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {

//        btnCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionbarSize = ScreenTools.dpToPx(56);
        toolbar.setTranslationY(-actionbarSize);
        mToolShadow.setTranslationY(-actionbarSize);
        ivLogo.setTranslationY(-actionbarSize);
        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        mToolShadow.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);

        ivLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        inboxMenuItem.getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        startContentAnimation();
                    }
                })
                .start();
    }

    /**
     * 更新fragment界面
     *
     * @param fragment
     */
    public void switchContent(final BeautyBaseFragment fragment, final View touchView, final String tag) {

        mContent = fragment;

        mPreContentTag = mContentTag;
        mContentTag = tag;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] startingLocation = new int[2];

                if (touchView == null) {
                    startingLocation[0] = 0;
                    startingLocation[1] = 0;
                } else {
                    touchView.getLocationOnScreen(startingLocation);
                    startingLocation[0] += touchView.getWidth() / 2;
                }

                fragment.getArguments().putIntArray(BeautyBaseFragment.ARG_REVEAL_START_LOCATION, startingLocation);

                getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment, tag).commit();
            }
        }, 450);


    }

    /**
     * 从堆栈中删除前一个fragment
     */
    public void removePreFragement() {

        Fragment pre = getSupportFragmentManager().findFragmentByTag(mPreContentTag);
        if (pre != null) {
            getSupportFragmentManager().beginTransaction().remove(pre).commit();
        }
//        List<Fragment> f = getSupportFragmentManager().getFragments();
//        if (f != null && f.size() > 1) {
//            for (int i = 0; i < f.size() - 1; i++) {
//                getSupportFragmentManager().beginTransaction().remove(f.get(i)).commit();
//            }
//        }
    }

}
