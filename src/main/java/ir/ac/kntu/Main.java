package ir.ac.kntu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 966, 720, true,
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
        AtomicInteger k = new AtomicInteger();
        VBox wholeBox = new VBox();
        wholeBox.setSpacing(4);
        wholeBox.setPadding(new Insets(5d));
        HBox firstRow = new HBox();
        firstRow.setSpacing(80);
        Button info = new Button("Info");
        wholeBox.getChildren().add(info);
        VBox enteries = new VBox();
        VBox state = new VBox();
        state.setSpacing(5);
        Label status = labelMaker("Status: Entering a, b, c, k", 20, "crimson",
                "white");
        state.getChildren().addAll(new ImageView(new Image("ir/ac/kntu/1.jpg")), status);
        status.setMinWidth(360);
        status.setMaxWidth(360);
        firstRow.getChildren().addAll(state, enteries);
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
        TextField K = new TextField();
        K.setFocusTraversable(false);
        K.setMaxSize(60, 16);
        K.setPromptText("k..");
        HBox secondRow = new HBox();
        enteries.getChildren().addAll(A, B, C, K);
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
        Button button = new Button("Draw");
        button.setPrefSize(60, 90);
        button.setTranslateX(20d);
        button.setTranslateY(40d);
        HBox lastBox = new HBox();
        root.setOnKeyPressed(event -> {
            if (A.getText().trim().matches("([0123456789])\\d*") &&
                    B.getText().trim().matches("([0123456789])\\d*") &&
                    C.getText().trim().matches("([0123456789])\\d*") &&
                    K.getText().trim().matches("([0123456789])\\d*")) {
                a.set(Integer.parseInt(A.getText().trim()));
                b.set(Integer.parseInt(B.getText().trim()));
                c.set(Integer.parseInt(C.getText().trim()));
                k.set(Integer.parseInt(K.getText().trim()));
                i.getAndIncrement();
                U.setText("U = " + calcU(a.get(), b.get(), c.get()));
                V.setText("V = " + calcV(a.get(), b.get(), c.get()));
                T.setText("T = " + calcT(a.get(), b.get(), c.get()));
                switch (i.get()) {
                    case 1:
                        status.setText("Status: Going To Laplace Domain");
                        StackPane pane = new StackPane();
                        pane.getChildren().add(new ImageView(new Image("ir/ac" +
                                "/kntu/2.png")));
                        pane.setStyle("-fx-border-style: DASHED;" +
                                "-fx-border-color: Cyan;" +
                                "-fx-border-radius: 4%");
                        pane.setPadding(new Insets(1, 4, 1, 4));
                        secondRow.getChildren().addAll(pane);
                        secondRow.setSpacing(4);
                        secondRow.setPadding(new Insets(4));
                        firstRow.getChildren().addAll(ans);
                        break;
                    case 5:
                        status.setText("Calculating L^(-1) (2nd Fragment - " +
                                "Part1)");
                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().add(new ImageView(new Image(
                                "ir/ac/kntu/4.png")));
                        stackPane.setStyle("-fx-border-style: DASHED;" +
                                "-fx-border-color: crimson;" +
                                "-fx-border-radius: 4%");
                        wholeBox.getChildren().add(stackPane);
                        break;
                    case 6:
                        status.setText("Calculating L^(-1) (2nd Fragment - " +
                                "Part2)");
                        StackPane stackPane1 = new StackPane();
                        stackPane1.getChildren().add(new ImageView(new Image(
                                "ir/ac/kntu/5.png")));
                        stackPane1.setStyle("-fx-border-style: DASHED;" +
                                "-fx-border-color: crimson;" +
                                "-fx-border-radius: 2%");
                        wholeBox.getChildren().add(stackPane1);
                        break;
                    case 7:
                        status.setText("End Of Calculations - Time Domain");
                        lastBox.getChildren().addAll(new ImageView(new Image(
                                "ir/ac/kntu/6.png")));
                        lastBox.setStyle("-fx-border-style: DASHED;" +
                                "-fx-border-color: orange;" +
                                "-fx-border-radius: 4%");
                        wholeBox.getChildren().add(lastBox);
                        break;
                    case 8:
                        lastBox.getChildren().addAll(button);
                        break;
                    case 2:
                        status.setText("Status: Separating Fragments");
                        ImageView image3 = new ImageView(new Image("ir/ac" +
                                "/kntu/3.png"));
                        image3.setTranslateY(20d);
                        Pane pane1 = new StackPane();
                        pane1.setStyle("-fx-border-style: DASHED;" +
                                "-fx-border-color: yellowgreen;" +
                                "-fx-border-radius: 5%");
                        pane1.getChildren().add(image3);
                        secondRow.getChildren().add(pane1);
                        break;
                    case 3:
                        status.setText("Status: Calculating T,U,V");
                        ans.getChildren().addAll(T, U, V);
                        break;
                    case 4:
                        status.setText("Calculating L^(-1) (First Fragment)");
                        break;
                    default:
                }
            }
        });
        button.setOnMouseClicked(event -> drawGraph(calcT(a.get(), b.get(), c.get())
                , calcU(a.get(), b.get(), c.get()), calcV(a.get(), b.get(),
                        c.get()), k.get(), root, a.get(), b.get(), c.get()));
        info.setFocusTraversable(false);
        info.setOnMouseClicked(event -> {
            Group root1 = new Group();
            Stage stage = new Stage();
            Scene scene = new Scene(root1, 308, 128, true,
                    SceneAntialiasing.BALANCED);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Info");
            stage.show();
            Label label = labelMaker("This program finds the answer of an " +
                    "ODE\nby using Laplace Transform.\nThis program was " +
                    "created for Differential \nEquations course" +
                    ".\nLecturer: Dr H.Aliakbarian\nCreator: S.Shayan " +
                    "Daneshvar", 18, "transparent", "Black");
            root1.getChildren().add(label);
        });
    }

    private void drawGraph(double t, double u, double v, double k, Group root
            , int a, int b, int c) {
        root.getChildren().clear();
        StackPane plane = new StackPane();
        root.getChildren().addAll(plane);
        NumberAxis yAxis = new NumberAxis();
        NumberAxis xAxis = new NumberAxis();
        LineChart lc = new LineChart<>(xAxis, yAxis);
        lc.setCreateSymbols(false);
        XYChart.Series dataSeries1 = new XYChart.Series();
        calcPoints(dataSeries1, t, u, v, k, a, b, c);
        lc.setAnimated(true);
        lc.setPrefSize(952, 698);
        lc.getData().addAll(dataSeries1);
        Button button = new Button("Try Again");
        ImageView logo = new ImageView(new Image("ir/ac" +
                "/kntu/logo.png"));
        logo.setScaleX(0.5d);
        logo.setScaleY(0.5d);
        logo.setStyle("-fx-opacity: 0.4");
        button.setTranslateX(340);
        button.setTranslateY(330);
        plane.getChildren().addAll(lc, button, logo);
//        plane.getChildren().addAll(lc);
        button.setOnMouseClicked(event -> {
            root.getChildren().clear();
            draw(root);
        });
    }

    private void calcPoints(XYChart.Series dataSeries, double t, double u,
                            double v, double k, int a, int b, int c) {
        dataSeries.setName("Answer");
        for (double x = 0d; x < 25d; x = x + 0.1d) {
            Pair<Double, Double> point = getPoint(t, u, v, k, a, b, c, x);
            dataSeries.getData().add(new XYChart.Data(point.getKey(),
                    point.getValue()));
        }

    }

    private Pair<Double, Double> getPoint(double t, double u, double v,
                                          double k, int a, int b, int c,
                                          double time) {
        Double result = Double.valueOf(0);
        result += k * t * Math.exp(c * time) + k * v * Math.exp((a * a / 4d - b - a / 2d) * time);
        System.out.println(result);
        double var = (b - a * a / 4) * time;
        result += k * Math.exp(-a * time / 2d) * (Math.cos(var)
                / u);
        System.out.println(result);
        Double rest = k * Math.exp(-a * time / 2d) * (a / 2d) * Math.sin(var) / (Math.sqrt
                (b - a * a / 4));
        System.out.println("Rest" + rest);
        if (!Double.isNaN(rest)) {
            System.out.println("Entered IF");
            result -= rest;
        }
        System.out.println(result);
        result = ((int) (result * 100)) / 100d;
        System.out.println(result);
        return new Pair<>(time, result);
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
