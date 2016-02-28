// Assignment #: 7
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th 3:00 - 4:15 PM
//  Arizona State University CSE205
//  Description: The Assignment 7 creates a WholePanel that is
//  an extension of JPanel, add it to its content, and set
//  a size for the applet.

import javax.swing.*;

public class Assignment7 extends JApplet
{

	public void init()
	{
    	WholePanel wholePanel = new WholePanel();
    	getContentPane().add(wholePanel);

    	setSize (400, 400);
  	}

}

