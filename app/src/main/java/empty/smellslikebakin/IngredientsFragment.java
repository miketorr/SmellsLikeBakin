package empty.smellslikebakin;

public class IngredientsFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.ingredients[index].split("`");// add each String at the index( whole recipe), and split the String into different strings(ingredients) to the array at "`"
    }
}
