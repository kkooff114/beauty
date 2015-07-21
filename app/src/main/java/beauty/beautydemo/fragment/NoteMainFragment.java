package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.NotesAdapter;
import beauty.beautydemo.adapter.NotesFragmentAdapter;
import beauty.beautydemo.adapter.SubscribeContentAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.bean.realm.NoteRealm;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.entity.NoteEntity;
import beauty.beautydemo.screens.NoteActivity;
import beauty.beautydemo.screens.NoteDetail;
import beauty.beautydemo.screens.SubscribeContentDetailActivity;
import beauty.beautydemo.tools.AppParms;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/6/28.
 */
public class NoteMainFragment extends BeautyBaseFragment {

    public static final String TITLE = "t";
    private String mTitle = "";

    @InjectView(R.id.rv_list)
    RecyclerView mList;

    NotesFragmentAdapter recyclerAdapter;

    public static NoteMainFragment newInstance(String title) {

        NoteMainFragment instance = new NoteMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.inject(this, view);

        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);


        return view;
    }

    private void setupList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);

        recyclerAdapter = new NotesFragmentAdapter(Constants.getNoteList(), getActivity());
        recyclerAdapter.setOnInViewClickListener(R.id.note_more,
                new NotesFragmentAdapter.onInternalClickListenerImpl() {
                    @Override
                    public void OnClickListener(View parentV, View v, Integer position, NoteEntity values) {
                        super.OnClickListener(parentV, v, position, values);
                        showPopupMenu(v, values);
                    }
                });
        recyclerAdapter.setOnInViewClickListener(R.id.notes_item_root,
                new NotesFragmentAdapter.onInternalClickListenerImpl() {
                    @Override
                    public void OnClickListener(View parentV, View v, Integer position, NoteEntity values) {
                        super.OnClickListener(parentV, v, position, values);

                        Intent intent = new Intent(getActivity(), SubscribeContentDetailActivity.class);
                        intent.putExtra(SubscribeContentAdapter.URL, values.getImage());
                        getActivity().startActivity(intent);
                    }
                });
        mList.setAdapter(recyclerAdapter);
    }

    private void showPopupMenu(View view, final NoteEntity note) {
        PopupMenu popup = new PopupMenu(getActivity(), view);

        popup.getMenuInflater()
                .inflate(R.menu.menu_notes_more, popup.getMenu());
        popup.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_forever:
                                Toast.makeText(getActivity(), "删除笔记", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.edit:
                                Toast.makeText(getActivity(), "编辑笔记", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.move_to_trash:
                                Toast.makeText(getActivity(), "移动到废纸篓", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });

        popup.show(); //showing popup menu
    }

    @Override
    public void onStateChange(int state) {
        super.onStateChange(state);
        if (RevealBackgroundView.STATE_FINISHED == state) {
            setupList();
        } else {

        }
    }
}
