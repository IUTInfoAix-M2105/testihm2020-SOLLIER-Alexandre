package fr.univ_amu.iut.ihm;

import fr.univ_amu.iut.utilitaires.Analyseur;
import fr.univ_amu.iut.utilitaires.ErreurDeSyntaxe;
import fr.univ_amu.iut.utilitaires.Expression;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TraceurDeFonction extends Application {

  GridPane root;

  Label expressionLabel;
  TextField expressionTextField;

  Button analyseButton;
  Label analysedExpressionLabel;

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

    setupAnalyseNodes();

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  void setupAnalyseNodes() {
    expressionLabel = new Label("Expression:");
    expressionTextField = new TextField("exp(-x * 0.2) * sin(x)");
    root.add(expressionLabel, 0, 0);
    root.add(expressionTextField, 1, 0);

    analyseButton = new Button("Analyser");
    root.add(analyseButton, 0, 1, 2, 1);

    analysedExpressionLabel = new Label();
    root.add(analysedExpressionLabel, 0, 2, 2, 1);

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
      votreIdentificateur.setId("zoneTraceCourbe");
      votreIdentificateur.setId("choixXMin");
      votreIdentificateur.setId("choixXMax");
      votreIdentificateur.setId("choixEspacementX_v1");
      votreIdentificateur.setId("choixEspacementX_v2");
      votreIdentificateur.setId("choixEspacementY_v1");
      votreIdentificateur.setId("choixEspacementY_v2");
      votreIdentificateur.setId("choixCouleur");
      votreIdentificateur.setId("choixEpaisseur");
      votreIdentificateur.setId("segmentsATracer");
      votreIdentificateur.setId("quadrillage");
      votreIdentificateur.setId("axeX");
      votreIdentificateur.setId("axeY");*/
  }

}