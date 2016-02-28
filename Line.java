// Assignment #: 7
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th 3:00 - 4:15 PM
//  Arizona State University CSE205
//  Description: This class contains all the information 
//					and the function to draw a line on our canvas class.

import java.awt.Graphics;
import java.awt.Color;

public class Line
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	// initializes all the data needed for the line
	public Line(int x1, int y1, int x2, int y2, Color color)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	// draws the line
	public void draw(Graphics page)
	{
		page.setColor(color);
		page.drawLine(x1, y1, x2, y2);
	}
}