package edu.asu.ser322;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionalListener implements ActionListener
{
	private Subroutine action;
	
	public FunctionalListener(Subroutine action)
	{
		this.action = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		action.perform();
	}
	
}
