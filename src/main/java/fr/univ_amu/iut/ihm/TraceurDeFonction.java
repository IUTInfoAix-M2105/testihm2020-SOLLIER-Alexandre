package fr.univ_amu.iut.ihm;

import fr.univ_amu.iut.utilitaires.Analyseur;
import fr.univ_amu.iut.utilitaires.ErreurDeSyntaxe;
import fr.univ_amu.iut.utilitaires.Expression;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TraceurDeFonction extends Application {

  GridPane root;

  Label expressionLabel;
  TextField expressionTextField;

  Button analyseButton;
  Label analysedExpressionLabel;

  Label minXLabel;
  Label maxXLabel;

  TextField minXTextField;
  TextField maxXTextField;

  Label xSpacingLabel;
  Label ySpacingLabel;

  Slider xSpacingSlider;
  Slider ySpacingSlider;

  TextField xSpacingTextField;
  TextField ySpacingTextField;

  Pane graph;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Traceur de fonction");
    stage.setWidth(600);
    stage.setHeight(650);
    stage.setResizable(false);

    root = new GridPane();
    root.setHgap(10);
    root.setVgap(3);

    setupAnalyseNodes();
    setupGraphPane();
    setupGraphNodes();

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  void setupAnalyseNodes() {
    expressionLabel = new Label("Expression:");
    expressionTextField = new TextField("exp(-x * 0.2) * sin(x)");
    root.add(expressionLabel, 0, 6);
    root.add(expressionTextField, 1, 6);

    analyseButton = new Button("Analyser");
    root.add(analyseButton, 0, 7, 2, 1);

    analysedExpressionLabel = new Label();
    root.add(analysedExpressionLabel, 0, 8, 2, 1);

    analyseButton.setOnAction(actionEvent -> {
      Analyseur analyseur = new Analyseur(expressionTextField.getText());

      try {
        Expression expression = analyseur.analyser();
        analysedExpressionLabel.setText("Expression analysée : f(x) = " + expression.toString());
      } catch (Exception e) {
        analysedExpressionLabel.setText(e.getLocalizedMessage());
      }
    });
  }

  void setupGraphPane() {

  }

  void setupGraphNodes() {
    minXLabel = new Label("Minimum abscisse");
    maxXLabel = new Label("Maximum abscisse");

    minXTextField = new TextField();
    maxXTextField = new TextField();

    minXTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
    maxXTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));

    minXTextField.setText("-10");
    maxXTextField.setText("10");

    root.add(minXLabel, 0, 0);
    root.add(maxXLabel, 1, 0);

    root.add(minXTextField, 0, 1);
    root.add(maxXTextField, 1, 1);

    xSpacingLabel = new Label("Espacement abscisse");

    xSpacingTextField = new TextField();
    xSpacingTextField.setTextFormatter(new TextFormatter<Double>(new StringConverter<>() {
      @Override
      public String toString(Double aDouble) {
        if (aDouble == null) return "0.5";

        if (aDouble < 0.5)
          return "0.5";
        else if (aDouble > 2)
          return "2";

        return String.valueOf(aDouble);
      }

      @Override
      public Double fromString(String s) {
        return Double.parseDouble(s);
      }
    }));

    xSpacingSlider = new Slider();
    xSpacingSlider.setMin(0.5);
    xSpacingSlider.setMax(2);
    xSpacingSlider.setValue(0.5);

    xSpacingTextField.textProperty().bindBidirectional(xSpacingSlider.valueProperty(), new NumberStringConverter());

    root.add(xSpacingLabel, 0, 2);
    root.add(xSpacingTextField, 0, 3);
    root.add(xSpacingSlider, 1, 3);

    ySpacingLabel = new Label("Espacement ordonnée");

    ySpacingTextField = new TextField();
    ySpacingTextField.setTextFormatter(new TextFormatter<Double>(new StringConverter<>() {
      @Override
      public String toString(Double aDouble) {
        if (aDouble == null) return "0.25";

        if (aDouble < 0.25)
          return "0.25";
        else if (aDouble > 2)
          return "2";

        return String.valueOf(aDouble);
      }

      @Override
      public Double fromString(String s) {
        return Double.parseDouble(s);
      }
    }));

    ySpacingSlider = new Slider();
    ySpacingSlider.setMin(0.25);
    ySpacingSlider.setMax(2);
    ySpacingSlider.setValue(0.25);

    ySpacingTextField.textProperty().bindBidirectional(ySpacingSlider.valueProperty(), new NumberStringConverter());

    root.add(ySpacingLabel, 0, 4);
    root.add(ySpacingTextField, 0, 5);
    root.add(ySpacingSlider, 1, 5);
  }

  void calculCoeffTransformationsAffines() {
  }
  	
  int transformationAffineY(double y) {
    return 0;
  }

  int transformationAffineX(double x) {
    return 0;
  }

  private void setIds() {
      expressionTextField.setId("texteAAnalyser");
      analysedExpressionLabel.setId("resultatAnalyse");
      analyseButton.setId("demandeAnalyser");
      /*votreIdentificateur.setId("demandeTracer");
      votreIdentificateur.setId("demandeEffacer");
      votreIdentificateur.setId("zoneTraceCourbe");*/
      minXTextField.setId("choixXMin");
      maxXTextField.setId("choixXMax");
      xSpacingSlider.setId("choixEspacementX_v1");
      xSpacingTextField.setId("choixEspacementX_v2");
      ySpacingSlider.setId("choixEspacementY_v1");
      ySpacingTextField.setId("choixEspacementY_v2");
      /*votreIdentificateur.setId("choixCouleur");
      votreIdentificateur.setId("choixEpaisseur");
      votreIdentificateur.setId("segmentsATracer");
      votreIdentificateur.setId("quadrillage");
      votreIdentificateur.setId("axeX");
      votreIdentificateur.setId("axeY");*/
  }

}