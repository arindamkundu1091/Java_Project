import javax.swing.JFrame;

class Main{
    public static void main(String[] args) {
        int starUnit = 800;
        int loading = 800;
        int delay = 60;
        try {
            JFrame mainFrame = new JFrame();
            
            mainFrame.setSize(800,800);
            mainFrame.setTitle("Star Projection");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setResizable(true);
            for(int j = 0, i = 0; i <= starUnit && j<=loading; j+=3, i+=3) {
                StarPanel sPanel = new StarPanel(i,j);
                mainFrame.add(sPanel);
                mainFrame.setVisible(true);
                Thread.sleep(delay);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}