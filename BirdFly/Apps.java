import javax.swing.*;


public class Apps {
    public static void main(String[] args) {
        int boardWith = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Burung Terabnang");
        frame.setSize(boardWith, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenu runThis = new MainMenu();
        frame.add(runThis);
        frame.pack();
        runThis.requestFocus();
        frame.setVisible(true);
    }
}