package beauty.beautydemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import beauty.beautydemo.getcolor.CameraView;
import beauty.beautydemo.getcolor.OnColorStatusChange;
import beauty.beautydemo.jni.ImageUtilEngine;

/**
 * Created by chenqiming on 2/2/15.
 */
public class GetColorFragment extends Fragment
{
	public GetColorFragment()
	{
	}

	private View root;
	private LayoutInflater inflater;
    private TextView colorText;
    private CameraView mSelfView;
    static ImageUtilEngine imageEngine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.inflater = inflater;
		root = inflater.inflate(R.layout.fragment_get_color, null);


		return root;
	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageEngine = new ImageUtilEngine();
        mSelfView = (CameraView) root.findViewById(R.id.self_view);
        colorText = (TextView) root.findViewById(R.id.color);
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
        });
    }

    public static ImageUtilEngine getImageEngine() {
        return imageEngine;
    }
}

