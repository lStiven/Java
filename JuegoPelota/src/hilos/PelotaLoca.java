/**
 * 
 */
package hilos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Stiven®
 * @Date 15/11/2017
 * @hour 9:22:57 p. m.
 * @projectName JuegoPelota
 *
 */
public class PelotaLoca extends JFrame implements MouseListener, KeyListener {

	boolean inicio = false; // Inicio del juego
	int posX = (int) (Math.random() * 440); // Posicion inicial de la pelota en X
	int posY = 30; // Posicion inicial de la pelota en Y
	int dX = 1; // Direccion de la pelota en X
	int dY = 1; // Direccion de la pelota en Y
	int posBX = 8; // Posicion inicial de la barra en X
	int barraSize = 100; //Tamaño de la barra
	int posBY = 580; // Posicion inicial de la barra en Y
	int posXAnterior; //Posicion de la pelota en X antes de las validaciones
	int posYAnterior; //Posicion de la pelota en Y antes de las validacionesz
	int lastRectPos; //Coordenada en X de el rectangulo antes de validar que tecla se oprimio

	public PelotaLoca() {
		super();
		setSize(500, 600);
		setBackground(Color.black);
		addMouseListener(this);
		addKeyListener(this);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g); // Pinta un nuevo juego por cada iteracion
		g.setColor(getBackground()); //Color del background
		g.fillOval(posXAnterior, posYAnterior, 10, 10); //Dibuja una pelota del color del background para ocultar su posicion anterior
		g.setColor(Color.red); // Color de la pelota
		g.fillOval(posX, posY, 10, 10); // Se crea la pelota con las nuevas coordenadas
		g.setColor(getBackground());
		g.fillRect(lastRectPos, posBY, barraSize, 20);
		g.setColor(Color.blue); // Color de la barra
		g.fillRect(posBX, posBY, barraSize, 20); // Se crea la barra
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		inicio = true; // si se hace click cambiara el estado de juego
		HiloJuego hilo1 = new HiloJuego(); // Se crea el hilo
		hilo1.start();
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		/**
		 * Cuando se presione flecha izquierda se dibujara una barra del color del background
		 * para ocultar la barra anterior y se restara 5 pixeles en X a la barra
		 * para dar la impresion que se mueve hacia la izquierda
		 */

		if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			lastRectPos = posBX;
			posBX = posBX - 10;			
			repaint();
		}
		/**
		 * Cuando se presione flecha derecha se dibujara una barra del color del background
		 * para ocultar la barra anterior y se sumara 5 pixeles en X a la barra
		 * para dar la impresion que se mueve hacia la derecha
		 */
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			lastRectPos = posBX;
			posBX = posBX + 10;
			repaint();
		}
	}

	public class HiloJuego extends Thread {

		public HiloJuego() {

		}

		public void run() {
			// Mientras el estado del juego sea "True" la pelota seguira rebotando
			while (inicio == true) {

				if (posY == 581) {
					inicio = false;
					System.out.println(posX + "   " + posY);
					JOptionPane.showMessageDialog(null, "Perdiste jajajajajaja");
				}
				
				/*
				 * cuando lapelota alcanze alguno de estos limites, su direccion cambiara
				 * ya sea negativa o positivamente en cualquiera de sus dos ejes 
				 */
				
				if ( posX == 482 )
					dX = -1;
				if ( posY == 580 && ((posX >= posBX) && (posX <= posBX+barraSize)))
					dY = -1;
				if ( posX == 8 )
					dX = 1;
				if ( posY == 30 )
					dY = 1;
				posXAnterior = posX;
				posYAnterior = posY;
				posX = posX + dX;
				posY = posY + dY;
				repaint();
				try {
					sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
