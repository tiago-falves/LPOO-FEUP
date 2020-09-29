package g24.model.menu;

import g24.model.Model;

public class GameWonModel extends Model {
    private String reason;

    public GameWonModel(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
