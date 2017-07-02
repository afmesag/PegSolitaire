package pegsolitaire;

import model.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.*;
import java.util.stream.Stream;

import com.sun.xml.internal.ws.handler.HandlerException;

public class SimulateNotation {
	
	private SimulateNotation(){}
	
	private static final Logger LOGGER = Logger.getLogger("logger");

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		setLogger();
		Path path = Paths.get("in");
		try (BufferedReader reader = Files.newBufferedReader(path); PrintWriter writer = new PrintWriter("out")) {
			Stream<String> lines = reader.lines();
			String result = game.simulateGame(lines);
			writer.write(result);
			reader.close();
			writer.close();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new IOException(e);
		}
	}

	private static void setLogger() {
		try {
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler("logger.xml", false);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new HandlerException(e); 
		}
	}
}
