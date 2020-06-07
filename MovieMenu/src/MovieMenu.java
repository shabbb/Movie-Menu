import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import org.apache.commons.io.FilenameUtils;

public class MovieMenu extends Frame implements ActionListener{

	JButton findMovie;
	JFileChooser movieChooser;
	JPanel movie;
	JPanel addMovie;
	String realMovieName;
	Desktop computer;
	
	public MovieMenu(){
		
		JFrame f = new JFrame("Movie Menu");
		movie = new JPanel();
		addMovie = new JPanel();
		
		BoxLayout movies = new BoxLayout(movie, BoxLayout.Y_AXIS);
		movie.setLayout(movies);
		
		BoxLayout button = new BoxLayout(addMovie, BoxLayout.Y_AXIS);
		addMovie.setLayout(button);
		
		findMovie = new JButton("Add Movie");
		movieChooser = new JFileChooser();
		movieChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		findMovie.addActionListener(this);
		
		addMovie.add(findMovie);
		
		
		
		
		JSplitPane moviePane = new JSplitPane(SwingConstants.HORIZONTAL, movie, addMovie);
		moviePane.setResizeWeight(1.0);
		
		f.add(moviePane);
		
		f.setSize(1000, 500);
		f.setVisible(true);
	}
	
	
	
	public static void main(String []args)
	{
		MovieMenu application = new MovieMenu();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == findMovie)
		{
			int value = movieChooser.showOpenDialog(addMovie);
			
			if (value == JFileChooser.APPROVE_OPTION)
			{
				File movieFile = movieChooser.getSelectedFile();
				realMovieName = FilenameUtils.removeExtension(movieFile.getName());
				JLabel movieName = new JLabel(realMovieName);
				movieName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				movieName.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						computer = Desktop.getDesktop();
						try {
							computer.open(movieFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						
					}
					
					@Override
					public void mouseExited (MouseEvent e) {
						
					}
				});
				
				movie.add(movieName);
				movie.validate();
				movie.repaint();
				System.out.println("Selected movie: " + realMovieName);
			}
		}
	}

}
