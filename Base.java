import java.util.ArrayList;

class Base {
  protected ArrayList<Exemple> exemples;

  public Base() {
    this.exemples = new ArrayList<Exemple>();

    this.exemples.add(new Exemple(0.5, 0, 0, 3));
    this.exemples.add(new Exemple(1, 1, 0, 1));
    this.exemples.add(new Exemple(0, 0, 0, 2));
    this.exemples.add(new Exemple(1, 0, 1, 2));
    this.exemples.add(new Exemple(0.5, 0, 1, 3));
    this.exemples.add(new Exemple(0.5, 0, 0, 2));
    this.exemples.add(new Exemple(0, 1, 0, 1));
    this.exemples.add(new Exemple(1, 1, 1, 3));
    this.exemples.add(new Exemple(1, 1, 1, 1));
    this.exemples.add(new Exemple(0, 1, 0, 2));
  }

  public ArrayList<Exemple> getExemples() {
    return this.exemples;
  }
}
