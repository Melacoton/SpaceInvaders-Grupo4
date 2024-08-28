package SpaceInvaders;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Image;

public class PanelGame extends JPanel implements ActionListener, KeyListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int playerX = 375;
    private int playerY = 490;
    
    private int playerWidth = 40, playerHeight = 60;
    private ArrayList<int[]> shots = new ArrayList<>();
    private ArrayList<int[]> enemies = new ArrayList<>();
    private ArrayList<int[]> enemyShots = new ArrayList<>();
private long LastShotTime = 0;
    private long ShootCooldown = 300;
    private boolean moveleft = false;
    private boolean moveright = false;
    private int enemyDirection = 1;
    private int enemySpeed = 1;
    
    private Timer timer;
    private int score = 0;
    private int level = 4;
    private int acumEnemigos = 10;
    private int moveDownDelay = 0;
    private boolean isGameOver = false;

    private Image playerI;
    private Image shotI;
    private Image enemieI;
    private Image fondoI;
    private Image gameOver;
    private Image nivel1;
    private Image nivel2;
    private Image nivel3;
    private Image tuto;
    
    public PanelGame() {
       // setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
        generateEnemies();

        playerI = new ImageIcon("src/imagenes/nena.png").getImage();
        shotI = new ImageIcon("src/imagenes/cokie.png").getImage();
        enemieI = new ImageIcon("src/imagenes/mike.png").getImage();
        fondoI = new ImageIcon("src/imagenes/boo.png").getImage();
        gameOver = new ImageIcon("src/imagenes/gameOver.png").getImage();
        tuto =  new ImageIcon("").getImage();
        nivel1 =  new ImageIcon("").getImage();
        nivel2 =  new ImageIcon("").getImage();
        nivel3 =  new ImageIcon("").getImage();
    }

    

    private void generateEnemies() {
        int enemyY = 50;
        int enemySpacing = 70;
        for (int i = 0; i < 10; i++) {
        	acumEnemigos=0;
        	if(level==0) {
                enemies.add(new int[]{i * enemySpacing + 50, enemyY=50, 1});	
                acumEnemigos=10;
        	}
        	if(level == 1) {
                enemies.add(new int[]{i * enemySpacing + 50, enemyY=50, 1});
                acumEnemigos=10;
            	}
            	if(level == 2) {
            	enemies.add(new int[]{i * enemySpacing + 50, enemyY=50, 1});
                enemies.add(new int[]{i * enemySpacing + 50, enemyY=100, 1});
                acumEnemigos=20;
            	}
            	if(level == 3) {
            	enemies.add(new int[]{i * enemySpacing + 50, enemyY=50, 1});
            	acumEnemigos=10;
            	}
            	if(level==4) {
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=30, 0});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=50, 0});
            		enemies.add(new int[]{i * enemySpacing + 50, enemyY=100, 1});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=190, 0});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=200, 0});
                    acumEnemigos=50;
            	}
            	if(level==5) {
            		enemies.add(new int[]{i * enemySpacing + 50, enemyY=50});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=100});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=150});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=150});
                    enemies.add(new int[]{i * enemySpacing + 50, enemyY=150});
                    acumEnemigos=50;
            	}
        }
    }

private void checkGameOver() {
        for (int[] enemy : enemies) {
            if (enemy[1] + 30 >= playerY) {  // 30 es la altura de los enemigos
                isGameOver = true;
                timer.stop();
                repaint();
                break;
            }
        }
    }

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(fondoI, 0, 0, getWidth(), getHeight(), this);
    g.drawImage(playerI, playerX, playerY, playerWidth, playerHeight, this);
    
    for (int[] shot : shots) {
        g.drawImage(shotI, shot[0], shot[1], 20, 20, this);
    }
    for (int[] enemy : enemies) {
    	if(enemy.length > 2 && enemy[2] == 1) {
        g.drawImage(enemieI, enemy[0], enemy[1], 40, 40, this);
    	}
    }
    for (int[] enemyShot : enemyShots) {
    	g.setColor(Color.WHITE);
        g.fillRect(enemyShot[0], enemyShot[1], 5, 10);
    }
    g.setColor(Color.WHITE);
    g.setFont(new Font("Nirmala UI", Font.BOLD, 23));
    g.drawString("Puntuación: " + score + " | Nivel: " + level, 3, 23);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Nirmala UI", Font.BOLD, 23));
    g.drawString("Enemigos restantes: "+ acumEnemigos,  470, 540);
    if (isGameOver) {
        g.drawImage(gameOver,  (getWidth() - 400) / 2,  (getHeight() - 90) / 2, 400, 90, this);
    }

}
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            Iterator<int[]> iterator = shots.iterator();
            while (iterator.hasNext()) {
                int[] shot = iterator.next();
                shot[1] -= 5;
                shot[0] += shot[2];
                if (shot[1] < 0 || shot[0] < 0 || shot[0] > getWidth()) {
                    iterator.remove();
                } else {
                    Iterator<int[]> enemyIterator = enemies.iterator();
                    while (enemyIterator.hasNext()) {
                        int[] enemy = enemyIterator.next();
                        if (shot[0] >= enemy[0] && shot[0] <= enemy[0] + 30 && shot[1] >= enemy[1] && shot[1] <= enemy[1] + 30) {
                            enemyIterator.remove();
                            iterator.remove();
                            score= score + 10;
                            acumEnemigos--;
                            break;
                        }
                    }
                }
            }

            if (level >= 2) {
                Iterator<int[]> enemyShotIterator = enemyShots.iterator();
                while (enemyShotIterator.hasNext()) {
                    int[] enemyShot = enemyShotIterator.next();
                    enemyShot[1] += 5;
                    if (enemyShot[1] > getHeight()) {
                        enemyShotIterator.remove();
                    } else if (enemyShot[0] >= playerX && enemyShot[0] <= playerX + playerWidth && enemyShot[1] >= playerY && enemyShot[1] <= playerY + playerHeight) {
                        isGameOver = true;
                        timer.stop();
                    }
                }
                if (new Random().nextInt(100) < 5 && !enemies.isEmpty()) {
                    int[] randomEnemy = enemies.get(new Random().nextInt(enemies.size()));
                    enemyShots.add(new int[]{randomEnemy[0] + 15, randomEnemy[1] + 30});
                }
            }
            if (level >= 1) {
                moveDownDelay++;
                if (moveDownDelay > 100) {
                    for (int[] enemy : enemies) {
                        enemy[1] += 10;
                    }
                    moveDownDelay = 5;
                }
            }

			if (level == 3 ) {
			    for (int[] enemy : enemies) {
			        if (enemy[0] + 30 / 2 >= playerX && enemy[0] + 30 / 2 <= playerX + playerWidth) {
			            if (enemy[1] < playerY - playerHeight) {
			                enemy[1] += 5;
			            } else if (enemy[1] + 30 >= playerY) {
			                isGameOver = true;
			                timer.stop();
			                break;
			            }
			        }
			    }
			}
			
			
        
			boolean shouldChangeDirection = false;
			
			if(level!=0) {
	        for (int[] enemy : enemies) {
	            enemy[0] += enemySpeed * enemyDirection;

	            if (enemy[0] <= 0 || enemy[0] >= getWidth() - 30) {
	                shouldChangeDirection = true;
	            }
	        }

	        if (shouldChangeDirection) {
	            enemyDirection *= -1;
	            for (int[] enemy : enemies) {
	                enemy[1] += 1;
	            }
	        }
			}
            if (enemies.isEmpty() && level <= 3) {
                level++;
                generateEnemies();
            }
         
	 if (moveleft && playerX > 0) {
                playerX -= 3;
            }
            if (moveright && playerX < getWidth() - playerWidth) {
                playerX += 3;
            }

checkGameOver();

            repaint();
        }
    }

 @Override
    public void keyPressed(KeyEvent e) {
        if (!isGameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moveleft = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveright = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - LastShotTime >= ShootCooldown) {
                    int speedX = 0;
                    if (moveleft) {
                        speedX = -2;
                    } else if (moveright) {
                        speedX = 2;
                    }
                    shots.add(new int[]{playerX + playerWidth / 2 - 2, playerY, speedX});
                    LastShotTime = currentTime;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveleft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveright = false;
        }
    }

 @Override
    public void keyTyped(KeyEvent e) {}
}