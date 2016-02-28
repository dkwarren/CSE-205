// Assignment #: 7
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th 3:00 - 4:15 PM
//  Arizona State University CSE205
//  Description: This creates our applet panels, buttons, and it draws organizes all of it.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WholePanel extends JPanel
{
	private Color currentColor;
	private CanvasPanel canvasPanel;
	private JPanel leftPanel;
	private Line line;
	private JButton undo;
	private JButton erase;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private ArrayList<Line> lineList = new ArrayList<Line>();
	private ArrayList<String> actionList = new ArrayList<String>();
	private ArrayList<Line> erasedList = new ArrayList<Line>();
	private JRadioButton black;
	private JRadioButton red;
	private JRadioButton blue;
	private JRadioButton green;
	private JRadioButton orange;

	public WholePanel()
	{
		ButtonGroup group = new ButtonGroup();

		currentColor = Color.black;

		black = new JRadioButton("Black");
		red = new JRadioButton("Red");
		blue = new JRadioButton("Blue");
		green = new JRadioButton("Green");
		orange = new JRadioButton("Orange");
		undo = new JButton("Undo");
		erase = new JButton("Erase");
		leftPanel = new JPanel();
		canvasPanel = new CanvasPanel();

		group.add(black);
		group.add(red);
		group.add(blue);
		group.add(green);
		group.add(orange);

		erase.addActionListener(new ButtonListener());
		undo.addActionListener(new ButtonListener());
		black.addActionListener(new ChoiceListener());
		red.addActionListener(new ChoiceListener());
		blue.addActionListener(new ChoiceListener());
		green.addActionListener(new ChoiceListener());
		orange.addActionListener(new ChoiceListener());

		canvasPanel.addMouseListener(new PointListener());
		canvasPanel.addMouseMotionListener(new PointListener());

		leftPanel.add(black);
		leftPanel.add(red);
		leftPanel.add(blue);
		leftPanel.add(green);
		leftPanel.add(orange);
		leftPanel.add(undo);
		leftPanel.add(erase);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, canvasPanel);
		sp.setDividerLocation(100);

		setLayout(new BorderLayout());
		add(sp);
	}

	private class ButtonListener implements ActionListener
	{	// erase/undo buttons
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();

			if(source == erase) {
				eraseList(erasedList);
				copyList(lineList, erasedList);
				eraseList(lineList);
				repaint();
				actionList.add("ERASE");
			}
			else if(source == undo) {
				if(actionList.size() > 0) {
					if(actionList.get(actionList.size() - 1) == "ERASE") {
						copyList(erasedList, lineList);
						repaint();
						actionList.add("UNDO");
					}
					else {
						deleteLastEntry(lineList);
						repaint();
						actionList.add("UNDO");
					}
				}
				else {
					deleteLastEntry(lineList);
					repaint();
					actionList.add("UNDO");
				}
			}
		}
		private void deleteLastEntry(ArrayList<Line> list)
		{
			list.remove(list.size() - 1);
		}
		private void eraseList(ArrayList<Line> list)
		{
			list.clear();
		}
		private void copyList(ArrayList<Line> source, ArrayList<Line> destination)
		{
			for(int x = 0; x < source.size(); x++)
				destination.add(source.get(x));
		}
	}	
	// The radio button listeners
	private class ChoiceListener implements ActionListener
	{	// radio buttons
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();

			if(source == black)
				currentColor = Color.black;
			else if(source == red)
				currentColor = Color.red;
			else if(source == blue)
				currentColor = Color.blue;
			else if(source == green)
				currentColor = Color.green;
			else if(source == orange)
				currentColor = Color.orange;
		}
	}
	// The mouse listeners
	private class CanvasPanel extends JPanel
	{
		public void paintComponent(Graphics page)
		{
			super.paintComponent(page);
			setBackground(Color.white);

			for(Line l : lineList)
				l.draw(page);
			/*for(int x = 0; x < lineList.size(); x ++) {
				Line drawLine = (Line)lineList.get(x);
				page.drawLine(x1, x2, y1, y2);
			}*/
		}
	}

	public class PointListener implements MouseListener, MouseMotionListener
	{
		public void mousePressed(MouseEvent event)
		{
			Point pt = event.getPoint();
			x1 = pt.x;
			y1 = pt.y;
		}	

		public void mouseReleased(MouseEvent event)
		{
			Point pt = event.getPoint();
			x2 = pt.x;
			y2 = pt.y;
			line = new Line(x1, y1, x2, y2, currentColor);
			lineList.add(line);
			repaint();
		}
		public void mouseDragged(MouseEvent event) 
		{
			Point pt = event.getPoint();
			x2 = pt.x;
			y2 = pt.y;
			line = new Line(x1, y1, x2, y2, currentColor);
			lineList.add(line);
			repaint();
		}
		public void mouseClicked (MouseEvent event) {}
    	public void mouseEntered (MouseEvent event) {}
    	public void mouseExited (MouseEvent event) {}
    	public void mouseMoved(MouseEvent event) {}
	}

}