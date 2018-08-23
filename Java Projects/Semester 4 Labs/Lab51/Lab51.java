import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.text.DecimalFormat;


class Lab51
{
    public static void main(String[] args)
    {
        CConverter cobaltConverter = new CConverter();  
    }
}

class CConverter extends JFrame
{
	public CConverter()
	{
		super("Cobalt Converter"); 
		buildGUI(); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true); 
	}
	
	public void buildGUI()
	{
		JLabel amount = new JLabel("Amount of Cobalt:");  
		JTextField amountInput = new JTextField(10);
		
		JLabel years = new JLabel("Number of Years:");
		JTextField yearsInput = new JTextField(10);
		
		JButton compute = new JButton("Compute");
		JButton quit = new JButton("Quit");
                
		JLabel result = new JLabel("Amount Left: ");
		
		Container container = getContentPane(); 
		container.setLayout(new GridBagLayout()); 
		
		GridBagConstraints gridBag = new GridBagConstraints();
		
		gridBag.fill = GridBagConstraints.BOTH;
		
                gridBag.insets = new Insets(15,15,15,15);
        
                gridBag.gridx = 0;		
                gridBag.gridy = 0;
		gridBag.gridwidth = 1;	
                gridBag.gridheight = 1;
		container.add(amount, gridBag);  
                     
		gridBag.gridx = 1;		
                gridBag.gridy = 0;
		gridBag.gridwidth = 1;
                gridBag.gridheight = 1;
		container.add(amountInput, gridBag); 
                  
		gridBag.gridx = 0;		
                gridBag.gridy = 1;
		gridBag.gridwidth = 1;
                gridBag.gridheight = 1;
		container.add(years, gridBag); 
		
		gridBag.fill = GridBagConstraints.NONE;
		gridBag.gridx = 1;		
                gridBag.gridy = 1;
		gridBag.gridwidth = 1;	
                gridBag.gridheight = 1;
		container.add(yearsInput, gridBag);

		gridBag.fill = GridBagConstraints.NONE;
		gridBag.gridx = 0;		
                gridBag.gridy = 2;
		gridBag.gridwidth = 1;	
                gridBag.gridheight = 1;
		container.add(compute, gridBag); 

		gridBag.fill = GridBagConstraints.NONE;
		gridBag.gridx = 1;		
                gridBag.gridy = 2;
		gridBag.gridwidth = 1;	
                gridBag.gridheight = 1;
		container.add(quit, gridBag); 

		gridBag.fill = GridBagConstraints.NONE;
		gridBag.gridx = 0;		
                gridBag.gridy = 3;
		gridBag.gridwidth = 3;	
                gridBag.gridheight = 1;
		container.add(result, gridBag); 
        
		QuitListener quitListener = new QuitListener(); 
		quit.addActionListener(quitListener); 
		AddListener addListener = new AddListener(amountInput , yearsInput, result);
		
                compute.addActionListener(addListener); 
	        yearsInput.addActionListener(addListener);
	}
	
	class AddListener implements ActionListener                                       
	{
		private JTextField input1;
		private JTextField input2;
		private JLabel output;
		
		AddListener(JTextField input1, JTextField input2, JLabel output) 
		{
			this.input1 = input1;
			this.input2 = input2;
			this.output = output;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			double cobaltAmount = (double) Double.parseDouble(input1.getText());
			double yearNumber = (double) Double.parseDouble(input2.getText());
                        double result = cobaltAmount * Math.pow((1 - 0.12), yearNumber); 
            
			DecimalFormat decForm = new DecimalFormat("#0.0000"); 
			
			output.setText("Amount Left: " + decForm.format(result)); 
		}
	}
	
	class QuitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
}
