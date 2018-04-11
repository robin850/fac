import java.util.ArrayList;

class Main {
  public static void main(String args[]) {
    Exemple exemple = new Exemple(1.0, 1, 0, 0);

    Base base = new Base();
    PPV ppv = new PPV();

    int k = 3;

    ArrayList<PPV.Pair> pairs = ppv.kPlusProches(base, exemple, k);

    System.out.println("Les " + k + " exemples les plus proches sont : ");
    System.out.println();

    System.out.println("|   X |  Y |  Z |  C |");

    for (PPV.Pair p : pairs) {
      System.out.print(p.exemple);
      System.out.println(" (Exemple n°" + p.index + ")");
    }
  }
}
