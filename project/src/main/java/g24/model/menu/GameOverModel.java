package g24.model.menu;

import g24.model.Model;

public class GameOverModel extends Model {
    private String reason;

    public GameOverModel(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
