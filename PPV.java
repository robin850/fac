import java.util.ArrayList;

class PPV {
  public class Pair {
    public int index;
    public double distance;
    public Exemple exemple;

    public Pair(int index, double distance, Exemple exemple) {
      this.index    = index + 1;
      this.distance = distance;
      this.exemple  = exemple;
    }
  }

  /**
   * Permet d'obtenir les k plus proches exemples d'un exemple donné
   * à partir d'une base.
   *
   * @param base    - Base contenant les exemples.
   * @param exemple - L'exemple avec lequel comparer.
   * @param k       - Le nombre d'exemples le plus proche.
   */
  public ArrayList<Pair> kPlusProches(Base base, Exemple exemple, int k) {
    ArrayList<Pair> distances = new ArrayList<Pair>();
    ArrayList<Pair> resultats = new ArrayList<Pair>();

    for (int i = 0; i < base.getExemples().size(); i++) {
      Exemple e = base.getExemples().get(i);

      distances.add(new Pair(i, exemple.distance(e), e));
    }

    /* == Debug ==  */
    // System.out.println("------------------------------");

    // for (Pair pair : distances) {
    //   System.out.println(pair.index + " : " + pair.distance);
    // }

    // System.out.println("------------------------------");
    /* == Debug == */

    // On trie le tableau dans l'ordre croissant
    distances.sort((d1, d2) -> {
      if (d1.distance > d2.distance)
        return 1;
      else if (d1.distance == d2.distance)
        return 0;
      else
        return -1;
    });

    // On prend les k premières valeurs
    for (int i = 0; i < k; i++)
      resultats.add(distances.get(i));

    return resultats;
  }
}
