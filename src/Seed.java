public class Seed implements Collectible {
  @Override
  public String getName() {
      return "Seed";
  }

  @Override
  public void use() {
      System.out.println("The bird pecks at the seed!");
  }
}
