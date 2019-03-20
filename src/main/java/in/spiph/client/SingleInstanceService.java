/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bennett.DenBleyker
 */
public class SingleInstanceService {

    public static final SingleInstanceService SIS = new SingleInstanceService("indicator.bin", "argument.txt");

    File indicatorFile;
    File argumentFile;

    public SingleInstanceService(String indicatorFilePath, String argumentFilePath) {
        this.indicatorFile = new File(indicatorFilePath);
        this.argumentFile = new File(argumentFilePath);
    }

    public boolean alreadyRunning() {
        if (this.indicatorFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.indicatorFile));
                Long t = Long.valueOf(br.readLine());
                return (t - System.currentTimeMillis()) > -5000;
            } catch (IOException | NumberFormatException ex) {
                this.indicatorFile.delete();
                return false;
            }
        } else {
            return false;
        }
    }

    public void setRunning(boolean running) {
        if (running) {
            this.indicatorFile.delete();
            try (FileWriter fw = new FileWriter(this.indicatorFile)) {
                fw.write(((Long) System.currentTimeMillis()).toString());
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Indicator File Failure", ex);
            }
        } else {
            this.indicatorFile.delete();
        }
    }

    List<String[]> args = new ArrayList();
    public String[] getNewArgs() {
        try {
            String[] result;
            try (Scanner in = new Scanner(this.argumentFile)) {
                String newArgs = "";
                while (in.hasNext()) {
                    newArgs += in.nextLine() + "\n";
                }
                for (String s : newArgs.split("\n")) {
                    args.add(s.split(" "));
                }
                result = args.get(0);
                args.remove(0);
                Logger.getLogger(SingleInstanceService.class.getName()).log(Level.INFO, "New arguments: {0}", newArgs.split("\n")[0]);
            }
            this.argumentFile.delete();
            return result;
        } catch (FileNotFoundException ex) {
            if (args.size() > 0) {
                String[] result = args.get(0);
                args.remove(0);
                Logger.getLogger(SingleInstanceService.class.getName()).log(Level.INFO, "New arguments: {0}", Arrays.toString(result));
                return result;
            }
            Logger.getLogger(SingleInstanceService.class.getName()).log(Level.FINER, "No new arguments");
        }
        return new String[] {};
    }

    public void setNewArgs(String[] args) throws IOException {
        String argsF = "";
        for (String arg : args) {
            argsF += arg + " ";
        }
        Files.write(this.argumentFile.toPath(), argsF.getBytes());
    }

}
