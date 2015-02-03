package beauty.beautydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenqiming on 2/3/15.
 */
public class GetFaceFragment extends Fragment {

    private View root;
    private LayoutInflater inflater;

    public GetFaceFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        root = inflater.inflate(R.layout.fragment_get_face, null);
        return root;
    }
}
