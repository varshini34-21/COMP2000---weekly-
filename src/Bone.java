public class Bone extends Item implements Collectible {
    public Bone() {
      super("Bone");
    }
  
    @Override
    public void use() {
      System.out.println("The dog gnaws on the bone!");
    }
  }
  