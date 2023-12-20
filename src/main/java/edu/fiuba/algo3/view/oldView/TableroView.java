package edu.fiuba.algo3.view.oldView;

import edu.fiuba.algo3.controller.BotonSiguienteJugadorHandler;
import edu.fiuba.algo3.controller.BotonTirarDadosHandler;
import edu.fiuba.algo3.model.AlgoRoma;
import edu.fiuba.algo3.model.Gladiador;
import edu.fiuba.algo3.view.newView.Camino;
import edu.fiuba.algo3.view.newView.Tablero;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class TableroView extends View {
    private final Stage STAGE;
    private final Scene SCENE;
    private final VBox LAYOUT;
    private final AlgoRoma JUEGO;
    private GridPane gridPane = new GridPane();
    private Gladiador jugadorActual;
    private final Camino CAMINO;
    private final edu.fiuba.algo3.view.newView.Tablero TABLERO;

    public TableroView(Stage stage, AlgoRoma juego, Camino camino) {
        this.STAGE = stage;
        this.LAYOUT = new VBox(SPACING);
        LAYOUT.setAlignment(Pos.CENTER);
        this.JUEGO = juego;
        this.jugadorActual = juego.obtenerJugadorActual();
        this.CAMINO = camino;
        this.TABLERO = new Tablero(juego.getMapa());

        actualizarTablero();

        SCENE = new Scene(LAYOUT, WIDTH, HEIGHT);
    }

    private void agregarBotonDados() {
        Button tirarDados = new Button("Tirar dados");
        tirarDados.setOnAction(new BotonTirarDadosHandler(STAGE, JUEGO, this));
        configurarBoton(tirarDados);
        LAYOUT.getChildren().add(tirarDados);
    }

    public void agregarBotonSiguienteJugador() {
        Button siguienteJugador = new Button("Siguiente");
        siguienteJugador.setOnAction(new BotonSiguienteJugadorHandler(this));
        configurarBoton(siguienteJugador);
        LAYOUT.getChildren().add(siguienteJugador);
    }

    public void actualizarMovimientos() {
        LAYOUT.getChildren().clear();
        configurarJugador();
        this.gridPane = TABLERO.paint();
        LAYOUT.getChildren().add(gridPane);
        CAMINO.paint(gridPane);
        agregarBotonSiguienteJugador();
    }

    public void actualizarTablero() {
        LAYOUT.getChildren().clear();
        configurarBackground();
        configurarJugador();
        this.jugadorActual = JUEGO.obtenerJugadorActual();
        this.gridPane = TABLERO.paint();
        LAYOUT.getChildren().add(gridPane);
        agregarBotonDados();
        CAMINO.paint(gridPane);
    }

    private void configurarJugador() {
        Label jugadorLabel = new Label("Jugador actual: " + this.jugadorActual.getNombre());
        Label energiaLabel = new Label("Energía: " + this.jugadorActual.obtenerPuntosEnergia());
        configurarTitulo(jugadorLabel, TXT_FONT, TITULO_FS);
        configurarTitulo(energiaLabel, TXT_FONT, TITULO_FS);
        LAYOUT.getChildren().add(jugadorLabel);
        LAYOUT.getChildren().add(energiaLabel);

    }

    private void configurarBackground() {
        Image pasto_tile = new Image(Objects.requireNonNull(View.class.getResource("/tile/pasto_tile.png")).toExternalForm());
        ImageView backgroundImageView = new ImageView(pasto_tile);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setSmooth(true); // Opcional: hace que el escalado sea más suave
        backgroundImageView.setCache(true); // Opcional: mejora el rendimiento
        BackgroundSize backgroundSize = new BackgroundSize(CELL_SIZE, CELL_SIZE, false, false, false, false);
        LAYOUT.setBackground(new Background(new BackgroundImage(backgroundImageView.getImage(), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize)));
    }

    public Scene getScene() {
        return SCENE;
    }
}