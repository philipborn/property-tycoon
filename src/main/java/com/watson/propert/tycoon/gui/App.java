/*
 * MIT License
 *
 * Copyright (c) 2020 Philip Borndalen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.watson.propert.tycoon.gui;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application {
  public String getGreeting() {
    return "Hello world.";
  }

  @Override
  public void start(Stage stage) throws Exception {

    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptGui.fxml");
    Parent root = FXMLLoader.load(fxmlUrl);
    stage.setTitle("Watson Game Property Tycoon - Project Team 16 - Sprint 3");
    stage.setScene(new Scene(root));
    stage.show();
  }

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(App.class);
    logger.debug("Hello world.");
    launch(args);
  }
}
