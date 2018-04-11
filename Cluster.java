import java.util.ArrayList;

class Cluster {
  protected ArrayList<Exemple> exemples;
  protected Exemple centre;

  public Cluster() {
    this.exemples = new ArrayList<Exemple>();
  }

  public ArrayList<Exemple> getExemples() {
    return this.exemples;
  }

  public void addExemple(Exemple e) {
    this.exemples.add(e);
  }

  public void removeExemple(Exemple e) {
    this.exemples.remove(e);
  }

  public Exemple getCentre() {
    return this.centre;
  }

  public void afficher() {
    System.out.println("Cluster : ");

    for (Exemple e : this.exemples) {
      System.out.println(e);
    }
  }
}
