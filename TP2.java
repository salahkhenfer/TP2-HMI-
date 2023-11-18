import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EditeurTexteApp extends Application {

    private TextArea zoneTexte;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Éditeur de texte");

        zoneTexte = new TextArea();
        zoneTexte.setWrapText(true);

        Button boutonOuvrir = new Button("Ouvrir");
        boutonOuvrir.setOnAction(e -> ouvrirFichier());

        Button boutonEnregistrer = new Button("Enregistrer");
        boutonEnregistrer.setOnAction(e -> enregistrerFichier());

        Button boutonQuitter = new Button("Quitter");
        boutonQuitter.setOnAction(e -> primaryStage.close());

        // Mise en page
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.getChildren().addAll(zoneTexte, boutonOuvrir, boutonEnregistrer, boutonQuitter);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ouvrirFichier() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String contenu = new String(Files.readAllBytes(selectedFile.toPath()));
                zoneTexte.setText(contenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enregistrerFichier() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            try {
                Path filePath = selectedFile.toPath();
                String contenu = zoneTexte.getText();
                Files.write(filePath, contenu.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
