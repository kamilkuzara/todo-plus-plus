package utils;

import model.Model;
import controllers.ListController;
import ui.LoginDialogLayout;
import ui.RegisterDialogLayout;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.geometry.Pos;

public class ComponentFactory{
    public static JFXRippler createListLink(Label label)
    {
        label.setStyle("-fx-padding: 15.0 0.0 15.0 10.0");
        label.setPrefWidth(297.0);

        JFXRippler rippler = new JFXRippler(label);
        rippler.setRipplerFill(Paint.valueOf("#d60d4d"));
        rippler.setAlignment(Pos.valueOf("BASELINE_LEFT"));

        return rippler;
    }

    public static JFXPopup createListMenu(ListController controller)
    {
        JFXButton deleteList = new JFXButton("Delete");
        FontIcon deleteListIcon = new FontIcon();
        deleteListIcon.setIconColor(Paint.valueOf("#454545"));
        deleteListIcon.setIconLiteral("gmi-delete");
        deleteListIcon.setIconSize(20);
        deleteList.setGraphic(deleteListIcon);
        deleteList.setAlignment(Pos.valueOf("BASELINE_LEFT"));
        deleteList.setMinWidth(120);
        deleteList.setOnMouseClicked(controller::deleteList);

        VBox container = new VBox(deleteList);

        JFXPopup menu = new JFXPopup();
        menu.setPopupContent(container);

        return menu;
    }

    public static StackPane createContentPlaceholder()
    {
        Label label = new Label("Select a TODO list to view");
        label.setStyle("-fx-text-fill: black; -fx-font-size: 30; -fx-label-padding: 0 0 0 20; -fx-opacity: 0.6;");

        StackPane container = new StackPane(label);

        return container;
    }

    public static JFXPopup createClipboardPopup()
    {
        Label clipboardLabel = new Label("Copy link to clipboard");

        VBox container = new VBox(clipboardLabel);

        JFXPopup popup = new JFXPopup();
        popup.setPopupContent(container);

        return popup;
    }

    public static JFXPopup createLoginMenu(JFXDialog dialog)
    {
        JFXPopup menu = new JFXPopup();

        JFXButton loginButton = new JFXButton("Log In");
        loginButton.setAlignment(Pos.valueOf("BASELINE_LEFT"));
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnMouseClicked(event -> {
            dialog.setContent(new LoginDialogLayout(dialog));
            dialog.show();
            menu.hide();
        });

        JFXButton registerButton = new JFXButton("Register");
        registerButton.setAlignment(Pos.valueOf("BASELINE_LEFT"));
        registerButton.setMaxWidth(Double.MAX_VALUE);
        registerButton.setOnMouseClicked(event -> {
            dialog.setContent(new RegisterDialogLayout(dialog));
            dialog.show();
            menu.hide();
        });

        VBox container = new VBox(loginButton, registerButton);
        container.setPrefWidth(150);

        menu.setPopupContent(container);

        return menu;
    }

    public static JFXPopup createLogoutMenu()
    {
        JFXPopup menu = new JFXPopup();

        JFXButton logoutButton = new JFXButton("Log Out");
        logoutButton.setAlignment(Pos.valueOf("BASELINE_LEFT"));
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setOnMouseClicked(event -> {
            menu.hide();
            Model.instance().logUserOut();
        });

        VBox container = new VBox(logoutButton);
        container.setPrefWidth(150);

        menu.setPopupContent(container);

        return menu;
    }

    public static FlowPane createUnsuccessfulLoginLabel()
    {
        FlowPane labelContainer = new FlowPane();
        labelContainer.getStylesheets().add("/styles/login_unsuccessful_label_styling.css");
        labelContainer.setId("labelContainer");

        Label label = new Label("Login unsuccessful");

        labelContainer.getChildren().add(label);

        return labelContainer;
    }
}
