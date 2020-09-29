package g24.model.menu;

import g24.model.Model;
import g24.model.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class MenuModel extends Model {
    private List<ButtonModel> buttonModels;
    private int selectedButtonIndex;

    public MenuModel() {
        buttonModels = new ArrayList<>();
        buttonModels.add(new ButtonModel("PLAY", "#5bc631", "#555555", new Position(35, 5), new Position(43, 8)));
        buttonModels.add(new ButtonModel("QUIT", "#c43334", "#555555", new Position(35, 11), new Position(43, 14)));
        buttonModels.get(0).setSelected(true);
        selectedButtonIndex = 0;
    }

    public void addButton(ButtonModel buttonModel) {
        buttonModels.add(buttonModel);
    }

    public List<ButtonModel> getButtonModels() {
        return buttonModels;
    }

    public int getNumberOfButtons() {
        return buttonModels.size();
    }

    public int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    public ButtonModel getSelectedButton() {
        return buttonModels.get(selectedButtonIndex);
    }

    public void selectNextButton() {
        getSelectedButton().setSelected(false);
        selectedButtonIndex++;
        if(selectedButtonIndex >= buttonModels.size()) selectedButtonIndex = 0;
        getSelectedButton().setSelected(true);
    }

    public void selectPreviousButton() {
        getSelectedButton().setSelected(false);
        selectedButtonIndex--;
        if(selectedButtonIndex < 0) selectedButtonIndex = buttonModels.size()-1;
        getSelectedButton().setSelected(true);
    }

}
