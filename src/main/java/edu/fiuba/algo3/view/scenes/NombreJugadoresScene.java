package edu.fiuba.algo3.view.scenes;

import edu.fiuba.algo3.controller.BotonIniciarTableroHandler;
import edu.fiuba.algo3.model.AlgoRoma;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NombreJugadoresScene extends SceneUtil {

    private static final int TEXTFIELD_W = 150;
    private static final int MINIMO_CARACTERES = 4;
    private final Stage STAGE;
    private final Scene SCENE;
    private final VBox LAYOUT;
    private final AlgoRoma JUEGO;
    private final ArrayList<TextField> INPUTS = new ArrayList<>();

    public NombreJugadoresScene(Stage stage, int cantidadJugdores, AlgoRoma juego) {
        this.STAGE = stage;
        this.LAYOUT = new VBox(SPACING);
        LAYOUT.setAlignment(Pos.CENTER);
        this.JUEGO = juego;

        configurarTitulo();
        configurarInputs(cantidadJugdores);
        configurarBotonComenzar();
        configurarBackground(LAYOUT);

        SCENE = new Scene(LAYOUT, WIDTH, HEIGHT);
    }

    private void configurarTitulo() {
        Label titulo = new Label("Elige a los jugadores");
        configurarTitulo(titulo, TXT_FONT, TITULO_FS);
        LAYOUT.getChildren().add(titulo);
    }

    private void configurarInputs(int cantidadJugadores) {
        for (int i = 0; i < cantidadJugadores; i++) {
            Label jugador = new Label("Gladiador " + (i + 1));
            configurarTitulo(jugador, TXT_FONT, TXT_FS);

            TextField nombre = new TextField();

            configurarTextField(nombre);
            INPUTS.add(nombre);

            HBox inputContainer = new HBox(SPACING);
            inputContainer.setAlignment(Pos.CENTER);
            inputContainer.getChildren().addAll(jugador, nombre);

            LAYOUT.getChildren().add(inputContainer);
        }
    }

    private void verificarInputs(Button comenzar) {
        for (TextField input : INPUTS) {
            input.textProperty().addListener((observable, oldValue, newValue) -> {
                if (input.getText().isEmpty() || input.getText().length() < MINIMO_CARACTERES) {
                    input.setStyle("-fx-background-color: #b01934; -fx-text-fill: white; -fx-background-radius: 5; -fx-font-weight: bold; -fx-alignment: center");
                } else {
                    input.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 5; -fx-font-weight: bold; -fx-alignment: center");
                }

                boolean inputsValidos = true;
                Set<String> textosInput = new HashSet<>();

                for (TextField input2 : INPUTS) {
                    String texto = input2.getText().trim();

                    if (texto.length() < MINIMO_CARACTERES) {
                        inputsValidos = false;
                    }

                    if (!textosInput.add(texto)) {
                        inputsValidos = false;
                        break;
                    }
                }

                comenzar.setDisable(!inputsValidos);
            });
        }
    }

    private void configurarTextField(TextField textField) {
        textField.setFont(Font.loadFont(getClass().getResourceAsStream(TXT_FONT), BTN_FS));
        textField.setStyle("-fx-background-color: #b01934; -fx-text-fill: white; -fx-background-radius: 5; -fx-font-weight: bold; -fx-alignment: center");
        textField.setPromptText("Nombre");
        textField.setMaxWidth(TEXTFIELD_W);
        textField.setMaxHeight(BTN_HEIGHT);
    }

    private void configurarBotonComenzar() {
        Button comenzar = new Button("Comenzar");
        configurarBoton(comenzar);
        comenzar.setDisable(true);
        comenzar.setOnAction(new BotonIniciarTableroHandler(INPUTS, STAGE, JUEGO));

        verificarInputs(comenzar);

        LAYOUT.getChildren().add(comenzar);
    }

    public Scene getScene() {
        return SCENE;
    }
}
