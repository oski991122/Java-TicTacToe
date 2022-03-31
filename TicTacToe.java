import java.awt.*;
import java.util.HashMap;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.math.*;

public class TicTacToe implements ActionListener
{
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player_turn;
	
	TicTacToe()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic Tac Toe!");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i=0;i<9;i++)
		{
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MVBoli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setForeground(new Color(0,0,0));
		}
		buttons[0].setText("O");
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		for(int i=0;i<9;i++)
		{
			if(e.getSource()==buttons[i])
			{
				
				if(buttons[i].getText()=="")
				{
					
					buttons[i].setText("X");
					
					textfield.setText("O turn");
					check();
				}
			}						
		}
		if(textfield.getText()!="You won")
		{
			buttons[computerMove()].setText("O");
			
			textfield.setText("X turn");
			check();
		}
	}
	
	
	public int computerMove()
	{
	  int bestScore = Integer.MIN_VALUE;
	  int move = 0;
	  String[] board = {"","","","","","","","",""};
	  for (int i = 0; i < 9; i++) 
	  {
		  board[i]=buttons[i].getText();
	      if (board[i].isEmpty()) 
	      {
	        board[i] = "O";
	        int score = minimax( 0, false, board);
	        board[i] = "";
	        
	        System.out.print("("+score+")");
        	
	        if (score > bestScore) 
	        {
	          bestScore = score;  
	          move = i;
	        }
	        
	        
	      }
	  }
	  System.out.println("\n");
	  for(int i=0;i<9;i++)
		  board[i]="";
	  
	  return move;
	}
	//#########MINIMAX################
	@SuppressWarnings("unused")
	public int minimax(int depth, boolean isMax, String[] board)
	{
		 int result = checkMinimax(board);
		 
		 if (result != 0) 
			 return result;
		 
		  
		
		 if (isMax) {
		    int bestScore = Integer.MIN_VALUE;
		    for (int i = 0; i < 9; i++) {
		      
		        // Is the spot available?
		        if (board[i].isEmpty()) {
		          board[i] = "O";
		          int score = minimax( depth + 1, false, board);
		          board[i] = "";
		          bestScore = Math.max(score, bestScore);
		        }
		      
		    }
		    
		    return bestScore;
		  } 
		 else 
		 {
			
		    int bestScore = Integer.MAX_VALUE;
		    for (int i = 0; i < 9; i++) {
		      
		        // Is the spot available?

		        if (board[i].isEmpty()) {
		        	
			        board[i] = "X";
			        int score = minimax(depth + 1, true, board);
			        board[i] = "";
			        bestScore = Math.min(score, bestScore);
		          
		        }
		      
		  	}
		    return bestScore;
		 }
	}
	
	public int checkMinimax(String[] board)
	{
		if((board[0]=="X"&&board[1]=="X"&&board[2]=="X")
		||(board[3]=="X"&&board[4]=="X"&&board[5]=="X")
		||(board[6]=="X"&&board[7]=="X"&&board[8]=="X")
		||(board[1]=="X"&&board[4]=="X"&&board[7]=="X")
		||(board[2]=="X"&&board[5]=="X"&&board[8]=="X")
		||(board[0]=="X"&&board[3]=="X"&&board[6]=="X")
		||(board[0]=="X"&&board[4]=="X"&&board[8]=="X")
		||(board[2]=="X"&&board[4]=="X"&&board[6]=="X"))
			return -1;
		else if((board[0]=="O"&&board[1]=="O"&&board[2]=="O")
		||(board[3]=="O"&&board[4]=="O"&&board[5]=="O")
		||(board[6]=="O"&&board[7]=="O"&&board[8]=="O")
		||(board[1]=="O"&&board[4]=="O"&&board[7]=="O")
		||(board[2]=="O"&&board[5]=="O"&&board[8]=="O")
		||(board[0]=="O"&&board[3]=="O"&&board[6]=="O")
		||(board[0]=="O"&&board[4]=="O"&&board[8]=="O")
		||(board[2]=="O"&&board[4]=="O"&&board[6]=="O"))
			return 1;
		
		
		return 0;
	}
	
	
	public void check()
	{
		if(
			(buttons[0].getText()=="X")&&
			(buttons[1].getText()=="X")&&
			(buttons[2].getText()=="X")
			)
			xWins(0,1,2);
		if(
			(buttons[3].getText()=="X")&&
			(buttons[4].getText()=="X")&&
			(buttons[5].getText()=="X")
			)
			xWins(3,4,5);
		if(
			(buttons[6].getText()=="X")&&
			(buttons[7].getText()=="X")&&
			(buttons[8].getText()=="X")
			)
			xWins(6,7,8);
		if(
			(buttons[0].getText()=="X")&&
			(buttons[3].getText()=="X")&&
			(buttons[6].getText()=="X")
			)
			xWins(0,3,6);
		if(
			(buttons[1].getText()=="X")&&
			(buttons[4].getText()=="X")&&
			(buttons[7].getText()=="X")
			)
			xWins(1,4,7);
		if(
			(buttons[2].getText()=="X")&&
			(buttons[5].getText()=="X")&&
			(buttons[8].getText()=="X")
			)
			xWins(2,5,8);
		if(
			(buttons[0].getText()=="X")&&
			(buttons[4].getText()=="X")&&
			(buttons[8].getText()=="X")
			)
			xWins(0,4,8);
		if(
			(buttons[2].getText()=="X")&&
			(buttons[4].getText()=="X")&&
			(buttons[6].getText()=="X")
			)
			xWins(2,4,6);
		if(
			(buttons[0].getText()=="O")&&
			(buttons[1].getText()=="O")&&
			(buttons[2].getText()=="O")
			)
			oWins(0,1,2);
		if(
			(buttons[3].getText()=="O")&&
			(buttons[4].getText()=="O")&&
			(buttons[5].getText()=="O")
			)
			oWins(3,4,5);
		if(
			(buttons[6].getText()=="O")&&
			(buttons[7].getText()=="O")&&
			(buttons[8].getText()=="O")
			)
			oWins(6,7,8);
		if(
			(buttons[0].getText()=="O")&&
			(buttons[3].getText()=="O")&&
			(buttons[6].getText()=="O")
			)
			oWins(0,3,6);
		if(
			(buttons[1].getText()=="O")&&
			(buttons[4].getText()=="O")&&
			(buttons[7].getText()=="O")
			)
			oWins(1,4,7);
		if(
			(buttons[2].getText()=="O")&&
			(buttons[5].getText()=="O")&&
			(buttons[8].getText()=="O")
			)
			oWins(2,5,8);
		if(
			(buttons[0].getText()=="O")&&
			(buttons[4].getText()=="O")&&
			(buttons[8].getText()=="O")
			)
			oWins(0,4,8);
		if(
			(buttons[2].getText()=="O")&&
			(buttons[4].getText()=="O")&&
			(buttons[6].getText()=="O")
			)
			oWins(2,4,6);
		
	}
	public void xWins(int a, int b, int c)
	{
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++)
			buttons[i].setEnabled(false);
		
		textfield.setText("You won");
	}
	public void oWins(int a, int b, int c)
	{
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++)
			buttons[i].setEnabled(false);
		
		textfield.setText("Computer won");
	}
}
