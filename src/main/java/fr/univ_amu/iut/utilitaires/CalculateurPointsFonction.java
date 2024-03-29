package fr.univ_amu.iut.utilitaires;

import java.io.IOException;
import java.util.ArrayList;

public class CalculateurPointsFonction {

  private static final int nombrePas = 1000;

  private ArrayList<Basic2DPoint> listePoints;
  private double yMin, yMax;

  public static void main(String[] args) {
    Analyseur analyseur = new Analyseur("exp(-x * 0.2) * sin(x)");

    try {
      Expression expression = analyseur.analyser();
      System.out.println(expression.toString());

      CalculateurPointsFonction calculateurPointsFonction = new CalculateurPointsFonction(expression, -0.5, 20);
      for (Basic2DPoint p : calculateurPointsFonction.getListePoints())
        System.out.println("f(" + p.getX() + ") = " + p.getY());

      System.out.println("Y Min: " + calculateurPointsFonction.getYMin());
      System.out.println("Y Max: " + calculateurPointsFonction.getYMax());
    } catch (IOException e) {
      System.err.println("L'expression a levée une IOException");
    } catch (ErreurDeSyntaxe e) {
      System.err.println("L'expression est mal formée");
    }
  }

  public CalculateurPointsFonction(Expression f, double xMin, double xMax) {

    listePoints = new ArrayList<>();

    double dx = (xMax - xMin) / nombrePas;
    yMin = f.valeur(xMin);
    yMax = yMin;

    for (double x = xMin; x <= xMax; x += dx) {
      double y = f.valeur(x);
      listePoints.add(new Basic2DPoint(x, y));
      yMin = Math.min(y, yMin);
      yMax = Math.max(y, yMax);
    }
  }

  public double getYMax() {
    return yMax;
  }

  public double getYMin() {
    return yMin;
  }

  public ArrayList<Basic2DPoint> getListePoints() {
    return listePoints;
  }

}
