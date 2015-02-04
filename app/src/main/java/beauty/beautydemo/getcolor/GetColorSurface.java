package beauty.beautydemo.getcolor;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by chenqiming on 2/3/15.
 */
public class GetColorSurface extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder surfaceHolder;
	private Camera camera;
	Thread previewThread;

	public GetColorSurface(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFormat(PixelFormat.RGB_332);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		camera = Camera.open(0);

		try
		{
			camera.setPreviewDisplay(holder);
			camera.setPreviewCallback(
					new Camera.PreviewCallback()
					{
						public void onPreviewFrame(byte[] data, Camera arg1)
						{
							//TODO
						}
					});
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		surfaceHolder.removeCallback(this);
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera = null;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
	{
		Camera.Parameters p = camera.getParameters();
		//p.setPictureSize(80, 60);
		//p.setColorEffect(android.hardware.Camera.Parameters.EFFECT_NONE);
		//p.setJpegQuality(10);
		//p.setPreviewFrameRate(15);
		//p.setPreviewFpsRange(10, 20);
		//p.setPreviewSize(80, 60);
		camera.setParameters(p);
		previewThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				camera.startPreview();
			}
		}, "preview_thread");
		previewThread.start();
	}
}
