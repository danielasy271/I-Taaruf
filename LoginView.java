/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.mycompany.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginView {
    private Stage stage;
    
    public LoginView(Stage stage) {
        this.stage = stage;
    }
    
    public void show() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f4f8;");
        
        // Logo section
        VBox logoBox = new VBox(10);
        logoBox.setAlignment(Pos.CENTER);
        
        // Try to load logo from file, if not found, create a styled placeholder
        ImageView logoView = null;
        try {
            Image logo = new Image(new FileInputStream("itaruf.png"));
            logoView = new ImageView(logo);
            logoView.setFitWidth(120);
            logoView.setFitHeight(120);
            logoView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            // Create a styled placeholder logo if file not found
            logoView = createPlaceholderLogo();
        }
        
        logoBox.getChildren().add(logoView);
        
        // Title
        Label titleLabel = new Label("I-Taaruf");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: #2c5f2d;");
        
        Label subtitleLabel = new Label("Shariah-Compliant Matchmaking System");
        subtitleLabel.setFont(Font.font("Arial", 14));
        subtitleLabel.setStyle("-fx-text-fill: #555;");
        
        // Login form box
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(400);
        formBox.setPadding(new Insets(30));
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        
        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(300);
        loginButton.setStyle("-fx-background-color: #2c5f2d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
        
        Button registerButton = new Button("Register New Account");
        registerButton.setMaxWidth(300);
        registerButton.setStyle("-fx-background-color: #4a7c59; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
        
        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText();
            
            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setContentText("Please enter both username and password");
                alert.showAndWait();
                return;
            }
            
            boolean success = UserManager.login(username, password);
            if (success) {
                DashboardView dashboard = new DashboardView(stage);
                dashboard.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });
        
        // Register button action
        registerButton.setOnAction(e -> {
            RegisterView registerView = new RegisterView(stage);
            registerView.show();
        });
        
        // Allow Enter key to submit login
        passwordField.setOnAction(e -> loginButton.fire());
        
        formBox.getChildren().addAll(usernameField, passwordField, loginButton, registerButton);
        root.getChildren().addAll(logoBox, titleLabel, subtitleLabel, formBox);
        
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    
    private ImageView createPlaceholderLogo() {
        // Create a simple SVG-style logo using JavaFX shapes
        StackPane logoStack = new StackPane();
        
        // Outer circle
        Circle outerCircle = new Circle(60);
        outerCircle.setFill(Color.web("#2c5f2d"));
        
        // Inner circle
        Circle innerCircle = new Circle(50);
        innerCircle.setFill(Color.web("#4a7c59"));
        
        // Text in center
        Label logoText = new Label("IT");
        logoText.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        logoText.setStyle("-fx-text-fill: white;");
        
        logoStack.getChildren().addAll(outerCircle, innerCircle, logoText);
        
        // Convert StackPane to ImageView (snapshot)
        javafx.scene.image.WritableImage image = logoStack.snapshot(null, null);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        
        return imageView;
    }
}
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}


