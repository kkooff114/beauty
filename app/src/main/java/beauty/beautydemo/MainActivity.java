package beauty.beautydemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    Menu menu;
    int actionSettingId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new TabFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        actionSettingId = R.id.action_settings_foundation;
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_settings_foundation)
        {
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_foundation);
            actionSettingId = R.id.action_settings_foundation;
            return true;
        }
        if (id == R.id.action_settings_blush)
        {
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_blush);
            actionSettingId = R.id.action_settings_blush;
            return true;
        }
        if (id == R.id.action_settings_lipgloss)
        {
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_lipgloss);
            actionSettingId = R.id.action_settings_lipgloss;
            return true;
        }
        if (id == R.id.action_settings_eyebrows)
        {
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_eyebrows);
            actionSettingId = R.id.action_settings_eyebrows;
            return true;
        }
        if (id == R.id.action_settings_eyeshadow)
        {
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_eyeshadow);
            actionSettingId = R.id.action_settings_eyeshadow;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
