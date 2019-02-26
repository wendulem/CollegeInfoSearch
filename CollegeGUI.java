import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CollegeGUI extends JFrame
{
  private JPanel new_panel;
  private String input_field;

  CollegeGUI()
  {
    super("College Build");
    new_panel = new JPanel();
    new_panel.setLayout(new BoxLayout(new_panel, BoxLayout.PAGE_AXIS));
    JLabel inputSchools = new JLabel("Input Your School");
    JTextField newField = new JTextField();
    JButton newButton = new JButton("Submit");
    newButton.addActionListener(new ActionListener()
    {

      public void actionPerformed(ActionEvent arg0)
      {
        // TODO Auto-generated method stub
        input_field = newField.getText();
        try
        {
          CollegeMain.createText();
        }
        catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    });
    newField.setMaximumSize(new Dimension(300, 30));
    new_panel.add(inputSchools);
    new_panel.add(newField);
    new_panel.add(newButton);
    this.add(new_panel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(300, 500));
    this.pack();
  }

  public JPanel getPanel()
  {
    return new_panel;
  }

  public String getInput()
  {
    return input_field;
  }
}
