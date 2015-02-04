package beauty.beautydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.inflater = inflater;
		root = inflater.inflate(R.layout.fragment_get_color, null);
		return root;
	}
}

