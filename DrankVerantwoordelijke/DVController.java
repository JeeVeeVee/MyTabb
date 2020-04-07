package DrankVerantwoordelijke;

import JDBC.*;
import errorHandling.MyError;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;
import java.util.List;

public class DVController {
    public TableView<Drank> tabel;
    public TableColumn<Drank, String> naamColumn;
    public TableColumn<Drank, Integer> voorraadColumn;
    public TableColumn<Drank, Drank> aanvulColumn;

    private ConnectionProvider provider;
    private DatabaseCommunicator dc;

    public DVController(ConnectionProvider provider){
        this.provider = provider;
        dc = new DatabaseCommunicator(this.provider.getConnection());
    }

    public void initialize(){
        fillTabel();

        naamColumn.setCellFactory(column -> {
            TableCell<Drank, String> cell = new TextFieldTableCell<>();
            return cell;
        });

        naamColumn.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getNaam()));

        voorraadColumn.setCellFactory(column -> {
            return new TextFieldTableCell<>();
        });

        voorraadColumn.setCellValueFactory(row -> new SimpleObjectProperty<>(row.getValue().getVoorraad()));

        aanvulColumn.setCellFactory(column -> {
            AanvulCell cell = new AanvulCell<>();
            Button button = cell.getButton();
            button.setOnAction(e -> {
                Drank drank = (Drank) cell.getTableRow().getItem();
                int aantal = Integer.parseInt(cell.getAantal().getText());
                vulAan(aantal, drank);
                tabel.getItems().clear();
                fillTabel();
                cell.getAantal().clear();
            });
            return cell;
        });

        aanvulColumn.setCellValueFactory(row ->
                new SimpleObjectProperty<>(row.getValue())
        );
    }

    public void vulAan(int aantal, Drank drank){
        dc.vulBij(aantal, drank);
    }

    public void fillTabel(){
        ObservableList<Drank> model = FXCollections.observableArrayList();
        List<Drank> dranklijst = null;
        try {
            dranklijst = dc.getAllDrank();
        } catch (SQLException e) {
            MyError error = new MyError("The drinks could not be retrieved from the database");
            error.launch();
            e.printStackTrace();
        }
        for(Drank drank : dranklijst){
            model.add(drank);
        }
        tabel.setItems(model);
    }
}
