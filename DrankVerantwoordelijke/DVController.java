package DrankVerantwoordelijke;

import JDBC.ConnectionProvider;
import JDBC.Drank;
import JDBC.Searcher;
import JDBC.Updater;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;
import java.util.List;

public class DVController {
    public TableView<Drank> tabel;
    public TableColumn<Drank, String> naamColumn;
    public TableColumn<Drank, Integer> voorraadColumn;

    private ConnectionProvider provider;
    private Searcher searcher;
    private Updater updater;

    public DVController(ConnectionProvider provider){
        this.provider = provider;
        searcher = new Searcher(provider.getConnection());
        updater = new Updater(provider.getConnection());

    }


    public void initialize(){
        ObservableList<Drank> model = FXCollections.observableArrayList();
        List<Drank> dranklijst = null;
        try {
            dranklijst = searcher.getAllDrank();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Drank drank : dranklijst){
            model.add(drank);
        }
        tabel.setItems(model);


        naamColumn.setCellFactory(column -> {
            TableCell<Drank, String> cell = new TextFieldTableCell<>();
            return cell;
        });

        naamColumn.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getNaam()));

        voorraadColumn.setCellFactory(column -> {
            return new TextFieldTableCell<>();
        });

        voorraadColumn.setCellValueFactory(row -> new SimpleObjectProperty<>(row.getValue().getVoorraad()));
    }
}
