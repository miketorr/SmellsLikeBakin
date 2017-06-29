package empty.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridFragment extends Fragment {


    public interface OnRecipeSelectedInterface {
        void onGridRecipeSelected(int index); // if tablet -  grid recipe rather than onlistrecipe to tell difference bettwen grid and list clicks
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);  // fragment_recyclerview into container, false - when onCreate returns automatically, false - so it returns once

//Grid adapter class for tablet
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        GridAdapter gridAdapter = new GridAdapter(listener);
        recyclerView.setAdapter(gridAdapter);

        //tablet measurement set
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics(); // get info on a diplsay like size and density
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int) (dpWidth/200);

        //set a layout manager for our recycler view - in our tablet we use GridLayoutManager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColumns);       //fragments always have access to their actvitity through their getActivity() method/ or getContext()
        recyclerView.setLayoutManager(layoutManager);

        return view;

    }
}


