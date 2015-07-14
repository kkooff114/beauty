package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.NoteMainActivity;

/**
 * Created by LJW on 15/6/28.
 */
public class NoteMainFragment extends BeautyBaseFragment implements View.OnClickListener {

    public static final String TITLE = "t";
    private String mTitle = "";

    private CardView cv_note_main_note;

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

        cv_note_main_note = (CardView) view.findViewById(R.id.cv_note_main_note);
        cv_note_main_note.setOnClickListener(this);


        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_note_main_note:
                Intent intent = new Intent(getActivity(), NoteMainActivity.class);

                startActivity(intent);

                break;
        }
    }
}
