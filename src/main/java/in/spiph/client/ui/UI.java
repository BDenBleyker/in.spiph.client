/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client.ui;

import in.spiph.info.Page;

/**
 *
 * @author Bennett.DenBleyker
 */
public interface UI {
    public abstract void displayPage(Page page);
    public abstract void handleUserInput(Object o);
}
