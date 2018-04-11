class Exemple {
  protected double X;
  protected int Y;
  protected int Z;
  protected int C;

  public Exemple(double X, int Y, int Z, int C) {
    this.X = X;
    this.Y = Y;
    this.Z = Z;
    this.C = C;
  }

  public double distance(Exemple b) {
    return Math.sqrt(
      Math.pow(X - b.getX(), 2)
    + Math.pow(Y - b.getY(), 2)
    + Math.pow(Z - b.getZ(), 2)
    );
  }

  public double getX() { return this.X; }
  public int getY()    { return this.Y; }
  public int getZ()    { return this.Z; }
  public int getC()    { return this.C; }

  public String toString() {
    return String.format("| %1.1f | %2d | %2d | %2d |", X, Y, Z, C);
  }
}
