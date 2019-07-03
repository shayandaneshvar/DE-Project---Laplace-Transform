package ir.ac.kntu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 954, 700, true,
                SceneAntialiasing.BALANCED);
        primaryStage.setTitle("Laplace Tutor");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Platform.runLater(() -> draw(root));
    }

    private void draw(Group root) {
        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();
        AtomicInteger c = new AtomicInteger();
        VBox wholeBox = new VBox();
        wholeBox.setSpacing(10);
        wholeBox.setPadding(new Insets(5d));
        HBox firstRow = new HBox();
        firstRow.setSpacing(80);
        VBox enteries = new VBox();
        firstRow.getChildren().addAll(new ImageView(new Image("ir/ac/kntu/1.jpg")), enteries);
        root.getChildren().addAll(wholeBox);
        TextField A = new TextField();
        A.setPromptText("a..");
        A.setFocusTraversable(false);
        enteries.setSpacing(4);
        enteries.setPadding(new Insets(12));
        A.setMaxSize(60, 16);
        TextField B = new TextField();
        B.setMaxSize(60, 16);
        B.setPromptText("b..");
        B.setFocusTraversable(false);
        TextField C = new TextField();
        C.setFocusTraversable(false);
        C.setMaxSize(60, 16);
        C.setPromptText("c..");
        HBox secondRow = new HBox();
        enteries.getChildren().addAll(A, B, C);
        wholeBox.getChildren().addAll(firstRow, secondRow);
        AtomicInteger i = new AtomicInteger(0);
        Label U = labelMaker("U = ", 24, "yellowGreen", "darkBlue");
        Label T = labelMaker("T = ", 24, "yellowGreen", "darkBlue");
        Label V = labelMaker("V = ", 24, "yellowGreen", "darkBlue");
        VBox ans = new VBox();
        T.setMaxWidth(350d);
        T.setMinWidth(350d);
        U.setMaxWidth(350d);
        U.setMinWidth(350d);
        V.setMaxWidth(350d);
        V.setMinWidth(350d);
        root.setOnKeyPressed(event -> {
            if (A.getText().trim().matches("([0123456789])\\d*") &&
                    B.getText().trim().matches("([0123456789])\\d*") &&
                    C.getText().trim().matches("([0123456789])\\d*")) {
                a.set(Integer.parseInt(A.getText().trim()));
                b.set(Integer.parseInt(B.getText().trim()));
                c.set(Integer.parseInt(C.getText().trim()));
                i.getAndIncrement();
                U.setText("U = " + calcU(a.get(), b.get(), c.get()));
                V.setText("V = " + calcV(a.get(), b.get(), c.get()));
                T.setText("T = " + calcT(a.get(), b.get(), c.get()));
                switch (i.get()) {
                    case 1:
                        secondRow.getChildren().addAll(new ImageView(new Image("ir/ac/kntu/2.png")));
                        firstRow.getChildren().addAll(ans);
                        break;
                    case 4:
                        wholeBox.getChildren().add(new ImageView(new Image(
                                "ir/ac/kntu/4.png")));
                        break;
                    case 5:
                        wholeBox.getChildren().add(new ImageView(new Image(
                                "ir/ac/kntu/5.png")));
                        break;
                    case 6:
                        wholeBox.getChildren().add(new ImageView(new Image(
                                "ir/ac/kntu/6.png")));
                        break;
                    case 2:
                        ImageView image3 = new ImageView(new Image("ir/ac" +
                                "/kntu/3.png"));
                        image3.setTranslateY(20d);
                        secondRow.getChildren().add(image3);
                        break;
                    case 3:
                        ans.getChildren().addAll(T, U, V);
                        break;
                }
            }
        });
    }

    public static Label labelMaker(String string, int size, String bColor,
                                   String fColor) {
        Label label = new Label(string);
        label.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,
                FontPosture.REGULAR, size));
        label.setStyle("-fx-background-color:" + bColor + ";-fx-padding: 10px" +
                ";-fx-background-radius: 7%;-fx-text-fill:" + fColor);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public static double calcU(int a, int b, int c) {
        return -calcT(a, b, c);
    }

    public static double calcV(int a, int b, int c) {
        return (c * c + a * c) / calcDet(a, b, c);
    }

    public static double calcT(int a, int b, int c) {
        return 1 / calcDet(a, b, c);
    }

    public static double calcDet(int a, int b, int c) {
        return c * c + b + a * c;
    }
}
