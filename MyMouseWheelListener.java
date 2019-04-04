import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyMouseWheelListener implements MouseWheelListener {
	
	private fenetreMolette fen ;
	
    public MyMouseWheelListener (fenetreMolette fen) {
        this.fen= fen ;
	}
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        int tour = e.getWheelRotation();
		if(tour < 0){//scroll vers le haut
			fen.tour(0);
		}else{//scroll vers la bas
           fen.tour(1);

	}
}
    
	
}

