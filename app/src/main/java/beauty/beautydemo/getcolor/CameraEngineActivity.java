package beauty.beautydemo.getcolor;

import beauty.beautydemo.R;
import beauty.beautydemo.jni.ImageUtilEngine;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class CameraEngineActivity extends Activity {
	private TextView colorText;
	private CameraView mSelfView;
	static ImageUtilEngine imageEngine;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camera_color_panel);
		imageEngine = new ImageUtilEngine();
		mSelfView = (CameraView) findViewById(R.id.self_view);
		colorText = (TextView)findViewById(R.id.color);
		mSelfView.setOnColorStatusChange(new OnColorStatusChange() {

			@Override
			public void onColorChange(int color) {
				// TODO Auto-generated method stub
				int r = Color.red(color);
				int g = Color.green(color);
				int b = Color.blue(color);
				Log.w("CameraEngineActivity", "R:" + r + " G:" + g + " B:" + b);
				colorText.setBackgroundColor(color);
			}

			@Override
			public void onSufaceViewDestrotyed() {

			}
		});
	}

	public static ImageUtilEngine getImageEngine() {
		return imageEngine;
	}
}