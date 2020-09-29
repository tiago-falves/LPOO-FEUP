package g24;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g24.controller.GameController;
import g24.controller.map.MapGenerator;
import g24.view.ViewFactory;
import g24.view.lanterna.LanternaFactory;

import java.io.IOException;

import static java.lang.System.exit;

public class Application {
    public static void main(String[] args) throws Exception {

        ViewFactory viewFactory = null;

        try {
            TerminalSize terminalSize = new TerminalSize(GameConfig.ROOM_WIDTH, GameConfig.ROOM_HEIGHT + 1);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            TerminalScreen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            viewFactory = new LanternaFactory(screen);
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }

        GameController game = new GameController(viewFactory);
        game.start();

        exit(0);
    }
}