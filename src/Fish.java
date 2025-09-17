public class Fish extends Item implements Collectible {
    public Fish() {
      super("Fish");
    }
  
    @Override
    public void use() {
      System.out.println("The cat eats the fish!");
    }
  }
  