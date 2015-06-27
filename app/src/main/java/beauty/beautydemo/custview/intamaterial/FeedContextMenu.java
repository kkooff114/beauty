package beauty.beautydemo.custview.intamaterial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import beauty.beautydemo.R;
import beauty.beautydemo.tools.ScreenTools;


/**
 * Created by froger_mcs on 15.12.14.
 */
public class FeedContextMenu extends LinearLayout implements View.OnClickListener{
    private static final int CONTEXT_MENU_WIDTH = ScreenTools.dpToPx(240);

    private int feedItem = -1;

    private OnFeedContextMenuItemClickListener onItemClickListener;

    public FeedContextMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_context_menu, this, true);

        findViewById(R.id.btnReport).setOnClickListener(this);
        findViewById(R.id.btnSharePhoto).setOnClickListener(this);
        findViewById(R.id.btnCopyShareUrl).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);

        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void bindToItem(int feedItem) {
        this.feedItem = feedItem;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(FeedContextMenu.this);
    }


    public void onReportClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onReportClick(feedItem);
        }
    }


    public void onSharePhotoClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onSharePhotoClick(feedItem);
        }
    }


    public void onCopyShareUrlClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onCopyShareUrlClick(feedItem);
        }
    }


    public void onCancelClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onCancelClick(feedItem);
        }
    }

    public void setOnFeedMenuItemClickListener(OnFeedContextMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReport:
                onReportClick();
                break;
            case R.id.btnSharePhoto:
                onSharePhotoClick();
                break;
            case R.id.btnCopyShareUrl:
                onCopyShareUrlClick();
                break;
            case R.id.btnCancel:
                onCancelClick();
                break;
        }
    }

    public interface OnFeedContextMenuItemClickListener {
        public void onReportClick(int feedItem);

        public void onSharePhotoClick(int feedItem);

        public void onCopyShareUrlClick(int feedItem);

        public void onCancelClick(int feedItem);
    }
}