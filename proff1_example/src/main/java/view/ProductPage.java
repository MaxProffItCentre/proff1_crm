package view;

import java.util.List;

import data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;
import service.ProductService;

public class ProductPage implements TemplatePage{
	private ObservableList<ProductViewer> products = FXCollections.observableArrayList();
	private TableView<ProductViewer> table = new TableView<ProductViewer>();
	private ProductService servisProduct = null;
	private Group group = new Group();
	
	public ProductPage(ProductService servisProduct){
		this.servisProduct = servisProduct;
		init();
	}
	private void init(){
		// From DataBase To ObservableList
		List<Product> list = servisProduct.getAllProducts();
		for(Product prod:list) {
			products.add(new ProductViewer(prod));
		}

		Label point = new Label("");
		
		Label label = new Label("Catalog Products");
		label.setLayoutX(140);
		label.setLayoutY(20);
		label.setStyle("-fx-font-size:20px");
				
		initTable();
		table.setLayoutX(120);
		table.setLayoutY(60);
		table.setStyle("-fx-font-size:16px");
		
		HBox controlPanel = new ControlPanel(this).getPanel();
		controlPanel.setLayoutX(120);
		controlPanel.setLayoutY(475);
		
		group.getChildren().add(point);
		group.getChildren().add(label);
		group.getChildren().add(table);
		group.getChildren().add(controlPanel);
	}	
	private void initTable(){	
		table.setEditable(true);

		TableColumn<ProductViewer, Integer> firstNameCol = new TableColumn<ProductViewer, Integer>("id");
		TableColumn<ProductViewer, String> secondNameCol = new TableColumn<ProductViewer, String>("name");
		TableColumn<ProductViewer, Integer> thirdNameCol = new TableColumn<ProductViewer, Integer>("code");

		firstNameCol.setCellValueFactory(new PropertyValueFactory<ProductViewer, Integer>("id"));
		secondNameCol.setCellValueFactory(new PropertyValueFactory<ProductViewer, String>("name"));
		thirdNameCol.setCellValueFactory(new PropertyValueFactory<ProductViewer, Integer>("code"));
		
		firstNameCol.setMinWidth(50);
		secondNameCol.setMinWidth(100);
		thirdNameCol.setMinWidth(50);

		table.setItems(products);

		// table.getColumns().addAll(firstNameCol,secondNameCol,thirdNameCol);
		table.getColumns().add(firstNameCol);
		table.getColumns().add(secondNameCol);
		table.getColumns().add(thirdNameCol);
		
		// Editable Name
		secondNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		secondNameCol.setOnEditCommit(new EventHandler<CellEditEvent<ProductViewer, String>>() {
			@Override
			public void handle(CellEditEvent<ProductViewer, String> value) {
				String str = value.getNewValue();
				int activeRow = value.getTablePosition().getRow();
				ProductViewer productViewer = value.getTableView().getItems().get(activeRow);
				productViewer.setName(str);
				
				// update product by DataBase
				servisProduct.updateProduct(productViewer.toProduct());
			}
		});
		// Editable Code
		thirdNameCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		thirdNameCol.setOnEditCommit(new EventHandler<CellEditEvent<ProductViewer, Integer>>() {
			@Override
			public void handle(CellEditEvent<ProductViewer, Integer> value) {
				Integer val = value.getNewValue();
				int activeRow = value.getTablePosition().getRow();
				ProductViewer productViewer = value.getTableView().getItems().get(activeRow);
				productViewer.setCode(val);
				
				// update product by DataBase
				servisProduct.updateProduct(productViewer.toProduct());
			}
		});
	}	

	public  Group getGroup(){
		return group;
	}

	@Override
	public void add() {
		System.out.println("add");		
	}
	@Override
	public void help() {
		System.out.println("help");
		
	}
	@Override
	public void remove() {
		System.out.println("remove");
	}
}
