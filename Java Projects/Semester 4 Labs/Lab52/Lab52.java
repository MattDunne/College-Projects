import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


class Lab52
{
    public static void main(String[] args)
    {
        Javapad pad = new Javapad(); 
    }
}

class Javapad extends JFrame
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItem, open, save, exit;
	
	public Javapad()
	{
		super("Javapad"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800,600)); 
		buildGUI(); 
		pack(); 
		setVisible(true); 
	}
    
	class MenuListener implements ActionListener 
	{
		private JFileChooser fileChooser;
		private JTextArea area;
                private String line;
       
		public MenuListener(JTextArea area)
		{
			fileChooser = new JFileChooser();
			this.area  = area;
		}
		
		public void actionPerformed(ActionEvent event)
		{
                    if(event.getSource() == newItem)  
                    {
                      area.setText(null); 
                    }
			
                    else if(event.getSource() == open) 
                    {
                        int returnVal = fileChooser.showOpenDialog(Javapad.this);
                        
                        BufferedReader buffRead = null;
                
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
                    
                            try
                            {
                                area.setText(null); 
                                File file = fileChooser.getSelectedFile(); 
                                buffRead = new BufferedReader(new FileReader(file)); 
                            }

                            catch(FileNotFoundException event2)
                            {
                                event2.printStackTrace();
                            }

                            try
                            {
                                line = buffRead.readLine(); 
                            }
                            catch(IOException event2) 
                            {
                                event2.printStackTrace();
                            }

                            while(line != null) 
                            {
                                area.append(line + "\n"); 

                                try
                                {
                                    line = buffRead.readLine(); 
                                }

                                catch(IOException event2)
                                {
                                    event2.printStackTrace(); 
                                }

                            }
                        }
                    }
            
                    else if(event.getSource() == save) 
                    {
                        JFileChooser saveFile = new JFileChooser(); 

                        int option = saveFile.showSaveDialog(save); 

                        if (option == JFileChooser.APPROVE_OPTION) 
                        {
                            try
                            {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(saveFile.getSelectedFile().getPath()));
                                buffWrite.write(this.area.getText());
                                buffWrite.close();
                            }

                            catch (Exception error) 
                            {
                                System.out.println(error.getMessage());
                            }
                        }
                    }

                    else if(event.getSource() == exit) 
                    {
                       System.exit(0);
                    }
            }
        }

	private void buildGUI()
	{
		Container container = this.getContentPane(); 
		
		menuBar = new JMenuBar(); 
		container.add(menuBar, BorderLayout.NORTH); 
		
		fileMenu = new JMenu("File"); 
		menuBar.add(fileMenu); 
		
		newItem = new JMenuItem("New"); 
		fileMenu.add(newItem); 
		fileMenu.addSeparator(); 
		
		open = new JMenuItem("Open"); 
		fileMenu.add(open); 
		fileMenu.addSeparator(); 
		
		save = new JMenuItem("Save"); 
		fileMenu.add(save); 
		fileMenu.addSeparator(); 
		
		exit = new JMenuItem("Exit"); 
		fileMenu.add(exit); 
		
		JTextArea edit = new JTextArea(30,20); 
		edit.setFont(new Font("Menlo", Font.PLAIN, 16)); 
		
		JScrollPane scrollPane = new JScrollPane(edit);  
		scrollPane.setPreferredSize(new Dimension(450, 100));
		container.add(scrollPane, BorderLayout.CENTER);
		
		MenuListener menuListener = new MenuListener(edit); 
		newItem.addActionListener(menuListener);
		open.addActionListener(menuListener);
		save.addActionListener(menuListener);
		exit.addActionListener(menuListener);
	}
}