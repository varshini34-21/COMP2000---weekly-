public class Seed extends Item implements Collectible {
    public Seed() {
      super("Seed");
    }
  
    @Override
    public void use() {
      System.out.println("The bird pecks at the seed!");
    }
  }
  