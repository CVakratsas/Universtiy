import javax.swing.JTextField;
import java.awt.event.*;

public class HintTextField extends JTextField implements FocusListener{
	
	private String hint;
	private boolean showingHint;
	
	public HintTextField(String hint) {
		super.setText(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		if(this.getText().isEmpty()) {
			super.setText("");
			this.showingHint = false;
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		if(this.getText().isEmpty()) {
			super.setText(hint);
			this.showingHint = true;
		}
		
	}
	
	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}
	
}
