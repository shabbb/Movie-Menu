import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import org.apache.commons.io.FilenameUtils;

public class MovieMenu extends Frame implements ActionListener{

	JButton findMovie;
	JFileChooser movieChooser;
	JPanel movie;
	JPanel addMovie;
	String realMovieName;
	Desktop computer;
	String extensionName;
	String movieDirectoryName = "E:\\Shabab\\Videos";
	List<File> movieFileList = new ArrayList<File>();
	File[] movieList;
	
	public MovieMenu(){
		
		JFrame f = new JFrame("Movie Menu");
		movie = new JPanel();
		//addMovie = new JPanel();
		
		BoxLayout movies = new BoxLayout(movie, BoxLayout.Y_AXIS);
		movie.setLayout(movies);
		JScrollPane movieListScroll = new JScrollPane(movie);
		movieListScroll.getVerticalScrollBar().setUnitIncrement(20);
		//movieListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	/*	BoxLayout button = new BoxLayout(addMovie, BoxLayout.Y_AXIS);
		addMovie.setLayout(button);
		
		findMovie = new JButton("Add Movies");
		movieChooser = new JFileChooser();
		movieChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		findMovie.addActionListener(this);
		
		addMovie.add(findMovie);
		
		
		
		
		JSplitPane moviePane = new JSplitPane(SwingConstants.HORIZONTAL, movie, addMovie);
		moviePane.setResizeWeight(1.0);
		
		f.add(moviePane);
		*/
		//File movieFiles = new File(movieDirectoryName);
		//File[] movieList = movieFiles.listFiles();
		
		findMovies(movieDirectoryName);
		
		
		for (File m: movieFileList)
		{
			File movieFile = m;
			extensionName = FilenameUtils.getExtension(movieFile.getName());
			//System.out.println(extensionName);
			
			if (extensionName.equals("mp4")||extensionName.equals("mov")||extensionName.equals("m4v")) {
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
			//System.out.println("Selected movie: " + realMovieName);
			
		}
		}
		f.getContentPane().add(movieListScroll);
		f.setSize(1000, 500);
		f.setVisible(true);
	}
	
	
	
	public static void main(String []args)
	{
		MovieMenu application = new MovieMenu();
	}
	//int counter = 0;
	public void findMovies(String movieDirectory)
	{
		File movieFiles = new File(movieDirectory);
		//System.out.println(movieFiles.getName());		
		movieList = movieFiles.listFiles();
		//counter+=1;
		//System.out.println(counter);
		/* System.out.println(movieList[0].getName());
		 for (File m: movieList)
		{
			System.out.println(m.getName());
		} */
		
		if (movieList != null)
		{
			for (File m: movieList)
			{
			//	System.out.println(m.getName());
			//	System.out.println(counter);
				if (m != null) {
			//	System.out.println(m.getName());
			//	System.out.println(counter);
				extensionName = FilenameUtils.getExtension(m.getName());
				if (m.isFile() & (extensionName.equals("mp4")||extensionName.equals("mov")||extensionName.equals("m4v")))
				{
					//System.out.println(m.getName());
					movieFileList.add(m);
				}
				
				else
				{
					//System.out.println(m.getName());
					//System.out.println(m.getAbsolutePath());
					findMovies(m.getAbsolutePath());
				}
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/* if (e.getSource() == findMovie)
		{
			
			File movieFiles = new File("C:\\Users\\Shabab\\Videos\\Captures");
			File[] movieList = movieFiles.listFiles();
			for (File m: movieList)
			{
				File movieFile = m;
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
				}); */
				
			
		
	}

}
