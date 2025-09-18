public class Fish implements Collectible {
  @Override
  public String getName() {
      return "Fish";
  }

  @Override
  public void use() {
      System.out.println("The cat eats the fish!");
  }
}
