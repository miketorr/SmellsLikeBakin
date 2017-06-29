package empty.smellslikebakin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {


    public interface OnRecipeSelectedInterface {
        void onListRecipeSelected(int index);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);  // fragment_recyclerview into container, false - when onCreate returns automatically, false - so it returns once


        //Put the recycler view in the fragment class
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        ListAdapter listAdapter = new ListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        //set a layout manager for our recycler view - layout manager responsible for where to place a view and when to reuse a view when no longer available
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());       //fragments always have access to their actvitity through their getActivity() method/ or getContext()
        recyclerView.setLayoutManager(layoutManager);

       return view;

    }
}
