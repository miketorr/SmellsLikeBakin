package empty.smellslikebakin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment {
    public static final String KEY_RECIPE_INDEX = "recipe index";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]); // set the top bar title in the activity
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        //create the fragment objects for each "tab"
        final CheckBoxesFragment ingredientsFragment = new IngredientsFragment();
        //pass the index with the KEY to the Ingredients bundle
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
        ingredientsFragment.setArguments(bundle);  //always set/  get


        final CheckBoxesFragment directionsFragment = new DirectionsFragment();
        bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
        directionsFragment.setArguments(bundle);

        //set the viewPager with the xml id (viewPager is the tabbed layout)
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {   //have to set the viewPager's adapter with *already created* FragmentPagerAdapter(getChild) - fragments withing fragments
            @Override
            public Fragment getItem(int position) {
                //setting wchih "tab" to return
                return position == 0 ? ingredientsFragment : directionsFragment;

            }
                /*  same as above statement^^
                if (position == 0) {
                    return ingredientsFragment;
                } else {
                    return directionsFragment;
                } */

            @Override
            public CharSequence getPageTitle(int position) {
                //set the title of our tabs in the viewpager
                return position == 0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                //# of tabs in fragment layout
                return 2;
            }
        });

        // to set tabs with view pager..
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name)); // to set title to app name from string resource files
    }
}
