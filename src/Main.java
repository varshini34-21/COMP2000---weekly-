import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Main().run());
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        Stage stage = new Stage();
        this.setContentPane(stage);

        this.setSize(700, 500); 
        this.setVisible(true);
    }

    public void run() {
       
        javax.swing.Timer timer = new javax.swing.Timer(50, e -> repaint());
        timer.start();
    }
}
