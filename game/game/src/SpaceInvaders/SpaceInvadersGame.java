package SpaceInvaders;

import javax.swing.JFrame;
import javax.swing.JPanel; 
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpaceInvadersGame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel contentPanel;
    private PanelGame gamePanel;
    private Menu menuPanel;

    public SpaceInvadersGame() {
        setTitle("Space Invaders");
        setSize(776, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        gamePanel = new PanelGame();
        menuPanel = new Menu(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(contentPanel, "game");
                    gamePanel.requestFocusInWindow();
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );

        contentPanel.add(menuPanel, "menu");
        contentPanel.add(gamePanel, "game");

        cardLayout.show(contentPanel, "menu");

        getContentPane().add(contentPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SpaceInvadersGame();
    }
}





