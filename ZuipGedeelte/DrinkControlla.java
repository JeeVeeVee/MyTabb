package ZuipGedeelte;

import JDBC.Bestelling;
import JDBC.DatabaseCommunicator;
import JDBC.Drank;
import JDBC.Leider;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkControlla {
    public Label loggedInAs;
    public AnchorPane anker;
    public AnchorPane gekozen;
    public Label titel;


    private DatabaseCommunicator dbc;
    private Leider leider;
    private Bestelling bestelling;
    private ArrayList<Node> toBeInvisibleUponChoiche;
    private List<Drank> allDrank;

    public DrinkControlla(Connection connection, Leider leider) {
        this.dbc = new DatabaseCommunicator(connection);
        this.leider = leider;
        try {
            this.allDrank = dbc.getAllDrank();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.bestelling = new Bestelling(leider, allDrank);
        toBeInvisibleUponChoiche = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < allDrank.size(); i += 4) {
            for (int j = 0; j + i < allDrank.size() && j < 4; j++) {
                final Drank drank = allDrank.get(j + i);
                DrankPane drankPane = new DrankPane(allDrank.get(j + i));
                drankPane.setLayoutX(50 + j * 200);
                drankPane.setLayoutY(125 + (i / 4) * 200);
                drankPane.setPrefWidth(500);
                drankPane.setPrefHeight(200);
                drankPane.setOnMouseClicked(e -> {
                    System.out.println("click");
                    gekozen.setVisible(true);
                    toBeInvisibleUponChoiche.forEach(node -> node.setVisible(false));
                    bestelling.addDrank(drank);
                    int x = 0;
                    for (Drank d : bestelling.getMap().keySet()) {
                        System.out.println(d.getNaam() + " : " + bestelling.getMap().get(drank));
                        Label label = new Label(d.getNaam() + " : " + bestelling.getMap().get(drank));
                        label.setFont(new Font(25));
                        label.setLayoutY(x * 30);
                        gekozen.getChildren().add(label);
                    }
                });
                anker.getChildren().add(drankPane);
                toBeInvisibleUponChoiche.add(drankPane);
            }
        }
        loggedInAs.setText("logged in as : " + leider.getFirst() + " " + leider.getLast());
        toBeInvisibleUponChoiche.add(titel);
    }
}
