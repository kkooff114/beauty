package beauty.beautydemo.screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.getcolor.CameraView;
import beauty.beautydemo.getcolor.OnColorStatusChange;
import beauty.beautydemo.jni.ImageUtilEngine;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LJW on 15/7/19.
 */
public class PickColorActivity extends BeautyBaseActivity {


    public static final String COLOR = "color";
    private TextView colorText;
    private CameraView mSelfView;
    static ImageUtilEngine imageEngine;

    int colorAll;

    @InjectView(R.id.button)
    Button mPickColorBtn;
    @InjectView(R.id.button2)
    Button mTryBtn;
    @InjectView(R.id.tv_color_backing)
    TextView mTVBacking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_get_color);
        ButterKnife.inject(this);

        imageEngine = new ImageUtilEngine();
        mSelfView = (CameraView) findViewById(R.id.self_view);
        colorText = (TextView) findViewById(R.id.color);
        mSelfView.setOnColorStatusChange(new OnColorStatusChange() {

            @Override
            public void onColorChange(int color) {
                // TODO Auto-generated method stub
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                Log.w("CameraEngineActivity", "R:" + r + " G:" + g + " B:" + b);
                colorAll = color;
                colorText.setBackgroundColor(color);
            }

            @Override
            public void onSufaceViewDestrotyed() {
                Intent intent = new Intent();
                intent.putExtra(COLOR, colorAll);
                setResult(0, intent);
                finish();
            }
        });
    }

    public static ImageUtilEngine getImageEngine() {
        return imageEngine;
    }

    @OnClick(R.id.button)
    void pickColor(View v) {
        mSelfView.setVisibility(View.GONE);
        mTVBacking.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.button2)
    void tryZ(View v) {
        Toast.makeText(PickColorActivity.this, "选取该颜色试妆", Toast.LENGTH_SHORT).show();
    }
}
