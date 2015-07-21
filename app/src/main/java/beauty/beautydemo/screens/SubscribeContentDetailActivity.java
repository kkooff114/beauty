package beauty.beautydemo.screens;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.SubscribeContentAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/8.
 */
public class SubscribeContentDetailActivity extends BeautyBaseActivity {

    @InjectView(R.id.wv_sub_content)
    WebView mWebView;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    private String url = "http://chuansong.me/n/332359";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_content_detail);
        ButterKnife.inject(this);

        setupToolBar();

        url = getIntent().getStringExtra(SubscribeContentAdapter.URL);
        setupWebView();
    }

    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgressWheel(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showProgressWheel(false);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(SubscribeContentDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                showProgressWheel(false);
            }
        });
        mWebView.loadUrl(url);
    }


    private void showProgressWheel(boolean visible) {
        progressWheel.setBarColor(getColorPrimary());
        if (visible) {
            if (!progressWheel.isSpinning())
                progressWheel.spin();
        } else {
            progressWheel.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (progressWheel.isSpinning()) {
                        progressWheel.stopSpinning();
                    }
                }
            }, 500);
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}
