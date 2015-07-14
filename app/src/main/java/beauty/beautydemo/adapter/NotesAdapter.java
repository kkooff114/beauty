package beauty.beautydemo.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Filter;
import android.widget.Filterable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.realm.NoteRealm;
import beauty.beautydemo.tools.TimeUtils;
import beauty.beautydemo.tools.ViewHelper;

/**
 * Created by lgp on 2015/4/6.
 */
public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    private final List<beauty.beautydemo.bean.realm.NoteRealm> originalList;
    private int upDownFactor = 1;
    private boolean isShowScaleAnimate = true;

    private int mDuration = 300;

    private Interpolator mInterpolator = new LinearInterpolator();

    private int mLastPosition = -1;

    private boolean isFirstOnly = true;

    protected Context mContext;
    private Map<Integer, onInternalClickListener> canClickItem;

    public NotesAdapter(List<beauty.beautydemo.bean.realm.NoteRealm> list) {

        originalList = new ArrayList<>(list);
    }

    public NotesAdapter(List<beauty.beautydemo.bean.realm.NoteRealm> list, Context context) {
        mContext = context;
        originalList = new ArrayList<>(list);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.notes_item_layout, parent, false);
        return new NotesItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder != null){
            addInternalClickListener(viewHolder.itemView, position, originalList.get(position));
        }
        NotesItemViewHolder holder = (NotesItemViewHolder) viewHolder;
        beauty.beautydemo.bean.realm.NoteRealm note = originalList.get(position);
        if (note == null)
            return;
        holder.setLabelText(note.getLabel());
        holder.setContentText(note.getContent());
        holder.setTimeText(TimeUtils.getConciseTime(note.getLastOprTime(), mContext));
        animate(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return originalList.size();
    }

    @Override
    public Filter getFilter() {
        return new NoteFilter(this, originalList);
    }

    protected Animator[] getAnimators(View view) {
        if (view.getMeasuredHeight() <=0 || isShowScaleAnimate){
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f);
            return new ObjectAnimator[]{scaleX, scaleY};
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f),
                ObjectAnimator.ofFloat(view, "translationY", upDownFactor * 1.5f * view.getMeasuredHeight(), 0)
        };
    }


    public void setList(List<beauty.beautydemo.bean.realm.NoteRealm> list) {
        notifyDataSetChanged();
        this.originalList.clear();
        originalList.addAll(list);
        setUpFactor();
        isShowScaleAnimate = true;
    }

    public void setDownFactor(){
        upDownFactor = -1;
        isShowScaleAnimate = false;
    }

    public void setUpFactor(){
        upDownFactor = 1;
        isShowScaleAnimate = false;
    }

    private static class NoteFilter extends Filter{

        private final NotesAdapter adapter;

        private final List<beauty.beautydemo.bean.realm.NoteRealm> originalList;

        private final List<beauty.beautydemo.bean.realm.NoteRealm> filteredList;

        private NoteFilter(NotesAdapter adapter, List<beauty.beautydemo.bean.realm.NoteRealm> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                for ( beauty.beautydemo.bean.realm.NoteRealm note : originalList) {
                    if (note.getContent().contains(constraint) || note.getLabel().contains(constraint)) {
                        filteredList.add(note);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.originalList.clear();
            adapter.originalList.addAll((ArrayList<beauty.beautydemo.bean.realm.NoteRealm>) results.values);
            adapter.notifyDataSetChanged();
        }
    }



    private void addInternalClickListener(final View itemV, final Integer position, final beauty.beautydemo.bean.realm.NoteRealm valuesMap) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemV.findViewById(key);
                final onInternalClickListener listener = canClickItem.get(key);
                if (inView != null && listener != null) {
                    inView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            listener.OnClickListener(itemV, v, position,
                                    valuesMap);

                        }
                    });
                    inView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            listener.OnLongClickListener(itemV, v, position,
                                    valuesMap);
                            return true;
                        }
                    });
                }
            }
        }
    }

    public void setOnInViewClickListener(Integer key, onInternalClickListener onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<>();
        canClickItem.put(key, onClickListener);
    }

    public interface onInternalClickListener {
        void OnClickListener(View parentV, View v, Integer position,
                             NoteRealm values);
        void OnLongClickListener(View parentV, View v, Integer position,
                                 NoteRealm values);
    }

    public static class onInternalClickListenerImpl implements onInternalClickListener{
        @Override
        public void OnClickListener(View parentV, View v, Integer position, NoteRealm values) {

        }

        @Override
        public void OnLongClickListener(View parentV, View v, Integer position, NoteRealm values) {

        }
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setStartPosition(int start) {
        mLastPosition = start;
    }

    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    protected void animate(RecyclerView.ViewHolder holder, int position){
        if (!isFirstOnly || position > mLastPosition) {
            for (Animator anim : getAnimators(holder.itemView)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);

            }
            mLastPosition = position;
        } else {
            ViewHelper.clear(holder.itemView);
        }
    }

}
