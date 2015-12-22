package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel {
	private String[] btnNames = {"F1 - HELP","F7 - NEW", "F8 - Remove"};
	private Button[] btns = new Button[btnNames.length];
	HBox hBox = new HBox(20);
	TemplatePage page;
	
	public ControlPanel(TemplatePage page){
		this.page = page;
		for(int i =0; i<btns.length;i++){
			btns[i] = new Button(btnNames[i]);
		}
		init();
	}
	public void init(){
		for(Button btn:btns){
			hBox.getChildren().add(btn);
		}
		btns[0].setOnAction(btn->page.help());
		btns[1].setOnAction(btn->page.add());
		btns[2].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				page.remove();				
			}
			
		});
	}
	public HBox getPanel(){
		return hBox;
	}
	
}
