public class Bone implements Collectible {
  @Override
  public String getName() {
      return "Bone";
  }

  @Override
  public void use() {
      System.out.println("The dog gnaws on the bone!");
  }
}
