package Controller;

public class SaveGameRequestHandler extends RequestHandler {
    private String pathToJsonFile;

    public SaveGameRequestHandler(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    @Override
    public void start() {

    }
}
