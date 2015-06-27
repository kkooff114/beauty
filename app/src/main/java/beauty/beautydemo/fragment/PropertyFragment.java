package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.cardview.CardGroup;
import beauty.beautydemo.custview.cardview.CardView;
import beauty.beautydemo.entity.MoveViewParms;

/**
 * Created by LJW on 15/3/17.
 */
public class PropertyFragment extends Fragment implements View.OnClickListener, CardView.onCardMoveListener {
//    private TextView title;
//    private TextView back;
//    private RelativeLayout title_bar;
    private static final String TEXTLIB = "我的档案";

    private CardGroup card_wrap;
    private PropertyAskFor myAskFor;
    private PropertyMyColor myColorCard;
    private PropertyMySkin mySkinCard;
    private PropertyMyProperty myProperty;

    private RadioGroup main_tab_group;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_property, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

//        title_bar = (RelativeLayout) getActivity().findViewById(R.id.title_bar);
        main_tab_group = (RadioGroup) getActivity().findViewById(R.id.main_tab_group);

//        title = (TextView) getActivity().findViewById(R.id.title);
//        title.setText(TEXTLIB);
//        back = (TextView) getActivity().findViewById(R.id.back);
//        back.setVisibility(View.GONE);

        card_wrap = (CardGroup) getActivity().findViewById(R.id.card_wrap);
        myAskFor = new PropertyAskFor(getActivity(),card_wrap, CardView.CARD_COLOR_GREEN,"提个要求");
        myAskFor.setListener(this);
        myColorCard = new PropertyMyColor(getActivity(),card_wrap, CardView.CARD_COLOR_ORANGE,"我的颜色");
        myColorCard.setListener(this);
        mySkinCard = new PropertyMySkin(getActivity(),card_wrap, CardView.CARD_COLOR_YELLOW,"我的肤质");
        mySkinCard.setListener(this);
        myProperty = new PropertyMyProperty(getActivity(),card_wrap, CardView.CARD_COLOR_RED,"美妆档案");
        myProperty.setListener(this);


        card_wrap.addCard(myProperty);
        card_wrap.addCard(mySkinCard);
        card_wrap.addCard(myColorCard);
        card_wrap.addCard(myAskFor);

        card_wrap.displayCards();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.title_bar:
//
//                AnimatorSet set = new AnimatorSet();
//                set.playTogether(
//                        ObjectAnimator.ofFloat(title_bar, "translationY", -300),
//                        ObjectAnimator.ofFloat(main_tab_group, "translationY", 300)
//                );
//                set.setDuration(1000).start();

//                break;
        }
    }

    @Override
    public List<MoveViewParms> onMove() {
        ArrayList<MoveViewParms> parmses = new ArrayList<MoveViewParms>();

//        MoveViewParms titleMVP = new MoveViewParms();
//        titleMVP.view = title_bar;
//        titleMVP.px = -getActivity().getResources().getDimension(R.dimen.title_bar_height);
//        parmses.add(titleMVP);


        MoveViewParms tabMVP = new MoveViewParms();
        tabMVP.view = main_tab_group;
        tabMVP.px = getActivity().getResources().getDimension(R.dimen.tab_menu_height);
        parmses.add(tabMVP);

        return parmses;
    }
}
