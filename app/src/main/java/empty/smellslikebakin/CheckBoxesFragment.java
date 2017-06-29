package empty.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public abstract class CheckBoxesFragment extends android.support.v4.app.Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //retrieve bundle with getARguments
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);

        //make string array of the ingredients or directions if boolean is true/false
        String[] contents = getContents(index);


        mCheckBoxes = new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState!=null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) { //check if there is already a saved instance state or nah
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        //create a linear layout
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBoxesLayout);
        setUpCheckBoxes(contents,linearLayout, checkedBoxes);


        return view;
    }


    //public method so we can extend this checkboxesfragment class in other classes
    //abstract method, do not create body{}
    public abstract String[] getContents(int index);


    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0; // use to iterate through our array
        for (String c : contents) {
            //create new Checkbox object and set how it looks
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8,16,8,16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(c);
            // add each created checkbox to the linear layout
            container.addView(mCheckBoxes[i]);
            if (checkedBoxes[i]) { //is true?
                mCheckBoxes[i].toggle();
            }
            i++;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox: mCheckBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked(); // = true or false
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes); // put into the bundle key, and array of booleans
        super.onSaveInstanceState(outState);
    }
}
