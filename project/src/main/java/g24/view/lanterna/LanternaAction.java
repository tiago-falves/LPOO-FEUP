package g24.view.lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

import g24.controller.commands.user.COMMAND;

public class LanternaAction {

    private Screen screen;
    public LanternaAction(Screen screen){
        this.screen = screen;
    }

    public COMMAND keyboardParser() {

        try{
            KeyStroke input = screen.pollInput();
            if (input == null) return COMMAND.NOTHING;
            KeyType keyType = input.getKeyType();

            switch (keyType){
                case ArrowDown: return COMMAND.DOWN;
                case ArrowUp: return COMMAND.UP;
                case ArrowLeft: return COMMAND.LEFT;
                case ArrowRight: return COMMAND.RIGHT;
                case Enter: return COMMAND.SELECT;
                case Character:
                    if(input.getCharacter() == ' ')
                        return COMMAND.SHOOT;
                    break;
                case EOF:
                case Escape: return COMMAND.QUIT;
            }
        } catch (IOException ignore) {

        }

        return COMMAND.NOTHING;
    }
}
