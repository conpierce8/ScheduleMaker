/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Connor
 */
public class ScheduleViewer extends Stage {
    private ArrayList<Schedule> schedules;
    private int[] statuses;
    private boolean allReviewed = false;
    private final int UNREVIEWED = 0, SAVED = 1, DELETED = 2, CURRENT = 3;
    private int index = 0, savedCount = 0;
    private final double WIDTH = 800, HEIGHT = 600;
    private File dir;
    private final Label progress;
    private final Button saveSched, discardSched;
    private final DecimalFormat outputFormat = new DecimalFormat("0000");
    private final DecimalFormat timeFormat = new DecimalFormat("00");
    
    public ScheduleViewer(ArrayList<Schedule> scheds) {
        schedules = scheds;
        statuses = new int[schedules.size()];
        statuses[0] = 2;
        java.util.Arrays.fill(statuses, UNREVIEWED);
        final Canvas c = new Canvas(WIDTH, HEIGHT);
        final TextField directory = new TextField();
        directory.setPromptText("Save schedules here");
        directory.setPrefWidth(400);
        directory.setEditable(false);
        final TextField prefix = new TextField();
        prefix.setPromptText("prefix");
        prefix.setPrefWidth(75);
        final TextField earliest = new TextField();
        earliest.setPrefWidth(45);
        earliest.setEditable(false);
        final TextField latest = new TextField();
        latest.setPrefWidth(45);
        latest.setEditable(false);
        if(schedules.size() > 0) {
            schedules.get(0).createImage(c);
            earliest.setText(getTimeString(schedules.get(0).earliestClass()));
            latest.setText(getTimeString(schedules.get(0).latestClass()));
        }
        Label label_earliest = new Label("Earliest class:");
        Label label_latest = new Label("Latest class:");
        progress = new Label("1 of "+schedules.size());
        final DirectoryChooser folderChooser = new DirectoryChooser();
        Button chooseDir = new Button("Choose...");
        chooseDir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                dir = folderChooser.showDialog(ScheduleViewer.this);
                directory.setText(dir.getAbsolutePath());
            }
        });
        saveSched = new Button("Save");
        discardSched = new Button("Discard");
        discardSched.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(allReviewed) {
                    int next = findNextSaved();
                    if(next < 0) {
                        c.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
                    } else {
                        schedules.get(index).createImage(c);
                        progress.setText((index+1) + " of " + schedules.size());
                    }
                    index = next;
                } else {
                    updateStatus(DELETED);
                    schedules.get(index).createImage(c);
                }
                if(index >= 0) {
                    earliest.setText(getTimeString(schedules.get(index).earliestClass()));
                    latest.setText(getTimeString(schedules.get(index).latestClass()));
                }
            }
        });
        HBox row1 = new HBox();
        row1.getChildren().add(directory);
        row1.getChildren().add(prefix);
        row1.getChildren().add(chooseDir);
        HBox row2 = new HBox();
        row2.getChildren().add(label_earliest);
        row2.getChildren().add(earliest);
        row2.getChildren().add(label_latest);
        row2.getChildren().add(latest);
        row2.getChildren().add(progress);
        row2.getChildren().add(saveSched);
        row2.getChildren().add(discardSched);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.getChildren().add(row1);
        vbox.getChildren().add(row2);
        vbox.getChildren().add(c);
        
        final Scene scene = new Scene(vbox, vbox.getBoundsInParent().getWidth(),
                vbox.getBoundsInParent().getHeight()+50);
        this.setScene(scene);
        saveSched.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(allReviewed) {
                    int prev = findPrevSaved();
                    if(prev < 0) {
                        c.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
                    } else {
                        index = prev;
                        schedules.get(index).createImage(c);
                        progress.setText((index+1) + " of " + schedules.size());
                    }
                } else {
                    updateStatus(SAVED);
                    javafx.scene.image.WritableImage im = new javafx.scene.image.WritableImage((int)scene.getWidth(), (int)scene.getHeight());
                    scene.snapshot(im);
                    String pre = prefix.getText();
                    if(pre == null || pre.equals("")) {
                        pre = "Schedule";
                    }
                    String path = directory.getText().equals("")?"":directory.getText()+System.getProperty("file.separator");
                    File outFile = new File(path+pre+"_"+outputFormat.format(savedCount)+".png");
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(im, null),
                                "png", outFile);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    //TODO: save images
                    //TODO: update to show earliest class
                    //TODO: update to show latest class
                    schedules.get(index).createImage(c);
                }
                earliest.setText(getTimeString(schedules.get(index).earliestClass()));
                latest.setText(getTimeString(schedules.get(index).latestClass()));
            }
        });
    }
    
    private int findPrevSaved() {
        for(int i = index-1; i >= 0; i--) {
            if(statuses[i] == SAVED) {
                return i;
            }
        }
        for(int i = statuses.length-1; i >= index; i--) {
            if(statuses[i] == SAVED) {
                return i;
            }
        }
        return -1;
    }
    
    private int findNextSaved() {
        for(int i = index+1; i<statuses.length; i++) {
            if(statuses[i] == SAVED) {
                return i;
            }
        }
        for(int i = 0; i <= index; i++) {
            if(statuses[i] == SAVED) {
                return i;
            }
        }
        return -1;
    }
    
    private void updateStatus(int status) {
       this.statuses[index] = status;
       if(status == SAVED){
           savedCount++;
       }
       if(index == statuses.length - 1) {
           index = 0;
           allReviewed = true;
           saveSched.setText("Prev");
           discardSched.setText("Next");
       } else {
           index++;
       }
       progress.setText((index+1)+" of "+schedules.size());
    }
    
    private String getTimeString(float time) {
        return timeFormat.format(time)+":"+timeFormat.format((time%1)*60);
    }
    
}
