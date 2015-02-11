package beauty.beautydemo.getcolor;

import beauty.beautydemo.jni.ImageUtilEngine;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

	private static String TAG = "CameraView";
	private SurfaceHolder mSurfaceHolder = null;
	private Camera mCamera = null;
	private int mPreviewHeight = 800; // 预览显示高度
	private int mPreviewWidth = 480; // 预览显示宽度
	private int mWidth = 0, mHeight = 0;
	private OnColorStatusChange colorChange;
    private ImageUtilEngine imageEngine;
	int colorOld;

	public CameraView(Context context) {
		super(context);
		initHolder();
	}

	public CameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHolder();
	}

	public CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHolder();
	}

	/**
	 * 初始化
	 */
	void initHolder() {
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	/**
	 * 大小发生改变的时候调用
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (holder.getSurface() == null) {
			return;
		}
		mSurfaceHolder = holder;
		initCamera();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();// 开启摄像头（2.3版本后支持多摄像头,需传入参数）
        imageEngine = new ImageUtilEngine();
		try {
			mCamera.setPreviewCallback(new PreviewCallback() {

				@Override
				public void onPreviewFrame(byte[] data, Camera camera) {
					mWidth = camera.getParameters().getPreviewSize().width;
					mHeight = camera.getParameters().getPreviewSize().height;
				}
			});
			mCamera.setPreviewDisplay(mSurfaceHolder);// 设置预览
		} catch (Exception ex) {
			if (null != mCamera) {
				mCamera.release();
				mCamera = null;
			}
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.v(TAG, "surfaceDestroyed");
		if (null != mCamera) {
			mCamera.setPreviewCallback(null); // ！！这个必须在前，不然退出出错
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}

		mSurfaceHolder = null;
	}

	/**
	 * 初始化Camera
	 */
	private void initCamera()// surfaceChanged中调用
	{
		Log.i(TAG, "going into initCamera");
		mCamera.stopPreview();// stopCamera();
		if (null != mCamera) {
			try {
				/* Camera Service settings */
				Camera.Parameters parameters = mCamera.getParameters();
				parameters.setFlashMode("off"); // 无闪光灯
				// 设置预览图片大小
				parameters.setPreviewSize(mPreviewHeight, mPreviewWidth); // 指定preview的大小
				parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);//视频自动对焦
				// 横竖屏镜头自动调整
				if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
					parameters.set("orientation", "portrait"); //
					// parameters.set("rotation", 90); // 镜头角度转90度（默认摄像头是横拍）
					mCamera.setDisplayOrientation(90); // 在2.2以上可以使用
				} else// 如果是横屏
				{
					parameters.set("orientation", "landscape"); //
					mCamera.setDisplayOrientation(0); // 在2.2以上可以使用
				}
				/* 视频流编码处理 */
				// 添加对视频流处理函数
				// 设定配置参数并开启预览
				mCamera.setParameters(parameters); // 将Camera.Parameters设定予Camera
				mCamera.setPreviewCallback(new PreviewCallback() {
					@Override
					public void onPreviewFrame(byte[] data, Camera camera) {
						// TODO Auto-generated method stub
						mWidth = camera.getParameters().getPreviewSize().width;
						mHeight = camera.getParameters().getPreviewSize().height;
						int[] buf = imageEngine.decodeYUV420SP(data, mWidth, mHeight);
						Bitmap bitmap = Bitmap.createBitmap(buf, mWidth,
								mHeight, Config.RGB_565);
						int color = bitmap.getPixel(mWidth / 2, mHeight / 2);
						if (ColorUtil.compareSpan(colorOld, color) > 10) {
							if (colorChange != null) {
                                colorChange.onColorChange(color);
							}
						}
						colorOld = color;
					}
				});
				mCamera.startPreview(); // 打开预览画面
				// 【调试】设置后的图片大小和预览大小以及帧率
				Camera.Size csize = mCamera.getParameters().getPreviewSize();
				mPreviewHeight = csize.height; //
				mPreviewWidth = csize.width;
				csize = mCamera.getParameters().getPictureSize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setOnColorStatusChange(OnColorStatusChange colorChange) {
		this.colorChange = colorChange;
	}
}
