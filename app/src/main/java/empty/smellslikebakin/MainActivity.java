package empty.smellslikebakin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tablet or nah, because they use different fragment layouts - list/grid
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if (!isTablet) {

            //create saved ListFragment to hold saved fragment, if none it is null
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);  // find fragment by TAG instead of by id
            if (savedFragment == null) {   // to prevent from creating multiple fragments each time screen rotated
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager(); // support fragments used becase AppCompat
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //add ListFragment to MainActivity's layout by using placeholder ViewGroup.
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT); //placeHolder is the id of activtiy main xml Frame Layout., third parameter- the TAG of our fragments
                fragmentTransaction.commit();
            }
        } else {
            //GridFragment used for TABLETS
            GridFragment savedFragment = (GridFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if (savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }
        }

    }




    @Override
    public void onListRecipeSelected(int index) {

        ViewPagerFragment fragment = new ViewPagerFragment();

        ///use bundles with fragments
        //passing an int number to get index in recipe list, from view pager fragment class// set a PSFS KEY in the class itself
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //add ListFragment to MainActivity's layout by using placeholder ViewGroup.
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null); // for back button, since we only go back once enter null
        fragmentTransaction.commit(); //save
    }


    @Override
    public void onGridRecipeSelected(int index) {
        DualPaneFragment fragment = new DualPaneFragment();

        ///use bundles with fragments
        //passing an int number to get index in recipe list, from view pager fragment class// set a PSFS KEY in the class itself
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //add ListFragment to MainActivity's layout by using placeholder ViewGroup.
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null); // for back button, since we only go back once enter null
        fragmentTransaction.commit(); //save

    }
}
