import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * 
 * @author Nicholas McCarty
 * @date 1/29/17
 * 
 * This program is designed to play the longest YEAH BOIII ever.
 *
 */
public class Window {
	
	private Clip clip;
	private JFrame frame = new JFrame();
	private boolean running;
	
	/*
	 * 
	 */
	public void createFrame() {
		frame.setTitle("Yeah BOIII");
		frame.getContentPane().setLayout(null);
		frame.pack();
		frame.setSize(500, 500);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(createButton());
		frame.add(createLabel());
		frame.setVisible(true);
	}
	
	/*
	 * 
	 */
	public JButton createButton() {
		JButton b = new JButton();
		  try {
			  InputStream in = getClass().getResourceAsStream("/Meme_Button.png");
			  BufferedImage buttonIcon = ImageIO.read(in);
			  b = new JButton(new ImageIcon(buttonIcon));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		b.setBounds(0, 0, 300, 300);
		b.setLocation(100, 20);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
		setRunning(false);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getRunning())
				{
					try {
						createClip();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					play(clip);
				}
				else
				{
					stop(clip);
					try {
						createClip();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					play(clip);
				}
				
			}
		});
		
		return b;
	}
	
	/*
	 * 
	 */
	public JLabel createLabel() {
		JLabel l = new JLabel();
		l.setText("Longest \"Yeah BOIII\" ever!");
		l.setBounds(10, 80, 200, 30);
		l.setLocation(175, 350);
		return l;
	}	
	
	/*
	 * 
	 */
	public void createClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Yeah Boiii.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		setClip(clip);
	}
	
	/*
	 * 
	 */
	public void play(Clip clip) {	
		clip.start();
		this.setRunning(true);
	}
	
	/*
	 * 
	 */
	public void stop(Clip clip) {
		clip.close();
		this.setRunning(false);
	}

	/*
	 * 
	 */
	public Clip getClip() {
		return clip;
	}

	/*
	 * 
	 */
	public void setClip(Clip clip) {
		this.clip = clip;
	}
	
	/*
	 * 
	 */
	public boolean getRunning() {
		return running;
	}
	
	/*
	 * 
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/*
	 * 
	 */
	public static void main(String[] args) {
		Window window = new Window();
		window.createFrame();
	}
	
}
