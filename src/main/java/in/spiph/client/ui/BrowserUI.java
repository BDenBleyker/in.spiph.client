/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client.ui;

import in.spiph.info.Page;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Bennett.DenBleyker
 */
public class BrowserUI implements UI {

    @Override
    public void displayPage(Page page) {
        File file = new File("test.html");
        try {
            Files.deleteIfExists(file.toPath());
            Files.write(file.toPath(), page.getHtml().getBytes());
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    @Override
    public void handleUserInput(Object o) {

    }

}
