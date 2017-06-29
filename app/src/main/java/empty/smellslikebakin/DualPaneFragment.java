package empty.smellslikebakin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualPaneFragment extends Fragment {
    private static final String INGREDIENTS_FRAGMENT = "ingredients_fragment";
    private static final String DIRECTIONS_FRAGMENT = "directions_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // to save instance/bundle info
        // same as view pager fragment

        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX); // same constant in viewpager fragment
        getActivity().setTitle(Recipes.names[index]); // set the top bar title in the activity
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false); // dual pane for tablet


        FragmentManager fragmentManager = getChildFragmentManager();

        IngredientsFragment savedIngredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDIENTS_FRAGMENT);
        if (savedIngredientsFragment==null) {
            CheckBoxesFragment ingredientsFragment = new IngredientsFragment();
            //pass the index with the KEY to the Ingredients bundle
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);

            fragmentManager.beginTransaction().add(R.id.leftPlaceholder,ingredientsFragment, INGREDIENTS_FRAGMENT).commit();
        }

        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);
        if (savedDirectionsFragment==null) {
            CheckBoxesFragment directionsFragment = new DirectionsFragment();
            //pass the index with the KEY to the Directions bundle
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);

            fragmentManager.beginTransaction().add(R.id.rightPlaceholder,directionsFragment, DIRECTIONS_FRAGMENT).commit();
        }
        

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name)); // to set title to app name from string resource files
    }
}
