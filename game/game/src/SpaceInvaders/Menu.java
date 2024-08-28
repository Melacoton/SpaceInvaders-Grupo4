package SpaceInvaders;


import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class Menu extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton startButton;
    private Image backgroundImage;

    public Menu(ActionListener startAction, ActionListener exitAction) {
        setLayout(null); 
        setPreferredSize(new Dimension(776, 600));

        backgroundImage = new ImageIcon(getClass().getResource("/imagenes/inicio.gif")).getImage();
        
        JLabel lblNewLabel = new JLabel("    PLAY GAME");
        lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(293, 252, 193, 48);
        add(lblNewLabel);
        
                startButton = new JButton("START GAME");
                startButton.setBounds(293, 252, 193, 48);
                startButton.setFont(new Font("Arial", Font.BOLD, 18));
                startButton.setBackground(new Color(91, 36, 67));
                startButton.setForeground(new Color(255, 255, 255));
                startButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
                startButton.addActionListener(startAction);
                
                        add(startButton);
                        startButton.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/inicio.gif")));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

