/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Connor
 */
public class ScheduleMaker extends Application {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Schedule> scheds = new ArrayList<>();
    
    @Override
    public void start(final Stage primaryStage) {
        final FileChooser fileChoose = new FileChooser();
        HBox hb1 = new HBox();
        final TextField tf1 = new TextField();
        tf1.setPromptText("Course Name");
        final TextField tf2 = new TextField();
        tf2.setPromptText("Course Number");
        Button b1 = new Button("Add Course");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Course c = new Course();
                c.setDescription(tf1.getText());
                c.setCourseNumber(tf2.getText());
                courses.add(c);
            }
        });
        hb1.getChildren().add(tf1);
        hb1.getChildren().add(tf2);
        hb1.getChildren().add(b1);
        
        int tfw = 75;
        
        VBox vb1 = new VBox();
        final TextField tf3 = new TextField();
        tf3.setPromptText("Course Name");
        final TextField tf4 = new TextField();
        tf4.setPromptText("Instructor");
        final TextField tf5 = new TextField();
        tf5.setPromptText("Section #");
        //Sunday inputs
        HBox hb_sun = new HBox();
        final CheckBox sun = new CheckBox();
        Text t_sun_1 = new Text("Sunday:");
        final TextField sun1 = new TextField();
        sun1.setPromptText("Hours");
        sun1.setPrefWidth(tfw);
        Text t_sun_2 = new Text(":");
        final TextField sun2 = new TextField();
        sun2.setPromptText("Minutes");
        sun2.setPrefWidth(tfw);
        Text t_sun_3 = new Text(" - ");
        final TextField sun3 = new TextField();
        sun3.setPromptText("Hours");
        sun3.setPrefWidth(tfw);
        Text t_sun_4 = new Text(":");
        final TextField sun4 = new TextField();
        sun4.setPromptText("Minutes");
        sun4.setPrefWidth(tfw);
        Text t_sun_5 = new Text(" at ");
        final TextField sun5 = new TextField();
        sun5.setPromptText("Room");
        sun5.setPrefWidth(tfw);
        hb_sun.getChildren().add(sun);
        hb_sun.getChildren().add(t_sun_1);
        hb_sun.getChildren().add(sun1);
        hb_sun.getChildren().add(t_sun_2);
        hb_sun.getChildren().add(sun2);
        hb_sun.getChildren().add(t_sun_3);
        hb_sun.getChildren().add(sun3);
        hb_sun.getChildren().add(t_sun_4);
        hb_sun.getChildren().add(sun4);
        hb_sun.getChildren().add(t_sun_5);
        hb_sun.getChildren().add(sun5);
        //end Sunday inputs
        //Monday inputs
        HBox hb_mon = new HBox();
        final CheckBox mon = new CheckBox();
        Text t_mon_1 = new Text("Monday:");
        final TextField mon1 = new TextField();
        mon1.setPromptText("Hours");
        mon1.setPrefWidth(tfw);
        Text t_mon_2 = new Text(":");
        final TextField mon2 = new TextField();
        mon2.setPromptText("Minutes");
        mon2.setPrefWidth(tfw);
        Text t_mon_3 = new Text(" - ");
        final TextField mon3 = new TextField();
        mon3.setPromptText("Hours");
        mon3.setPrefWidth(tfw);
        Text t_mon_4 = new Text(":");
        final TextField mon4 = new TextField();
        mon4.setPromptText("Minutes");
        mon4.setPrefWidth(tfw);
        Text t_mon_5 = new Text(" at ");
        final TextField mon5 = new TextField();
        mon5.setPromptText("Room");
        mon5.setPrefWidth(tfw);
        hb_mon.getChildren().add(mon);
        hb_mon.getChildren().add(t_mon_1);
        hb_mon.getChildren().add(mon1);
        hb_mon.getChildren().add(t_mon_2);
        hb_mon.getChildren().add(mon2);
        hb_mon.getChildren().add(t_mon_3);
        hb_mon.getChildren().add(mon3);
        hb_mon.getChildren().add(t_mon_4);
        hb_mon.getChildren().add(mon4);
        hb_mon.getChildren().add(t_mon_5);
        hb_mon.getChildren().add(mon5);
        //end Monday inputs
        //Tuesday inputs
        HBox hb_tue = new HBox();
        final CheckBox tue = new CheckBox();
        Text t_tue_1 = new Text("Tuesday:");
        final TextField tue1 = new TextField();
        tue1.setPromptText("Hours");
        tue1.setPrefWidth(tfw);
        Text t_tue_2 = new Text(":");
        final TextField tue2 = new TextField();
        tue2.setPromptText("Minutes");
        tue2.setPrefWidth(tfw);
        Text t_tue_3 = new Text(" - ");
        final TextField tue3 = new TextField();
        tue3.setPromptText("Hours");
        tue3.setPrefWidth(tfw);
        Text t_tue_4 = new Text(":");
        final TextField tue4 = new TextField();
        tue4.setPromptText("Minutes");
        tue4.setPrefWidth(tfw);
        Text t_tue_5 = new Text(" at ");
        final TextField tue5 = new TextField();
        tue5.setPromptText("Room");
        tue5.setPrefWidth(tfw);
        hb_tue.getChildren().add(tue);
        hb_tue.getChildren().add(t_tue_1);
        hb_tue.getChildren().add(tue1);
        hb_tue.getChildren().add(t_tue_2);
        hb_tue.getChildren().add(tue2);
        hb_tue.getChildren().add(t_tue_3);
        hb_tue.getChildren().add(tue3);
        hb_tue.getChildren().add(t_tue_4);
        hb_tue.getChildren().add(tue4);
        hb_tue.getChildren().add(t_tue_5);
        hb_tue.getChildren().add(tue5);
        //end Tuesday inputs
        //Wednesday inputs
        HBox hb_wed = new HBox();
        final CheckBox wed = new CheckBox();
        Text t_wed_1 = new Text("Wednesday:");
        final TextField wed1 = new TextField();
        wed1.setPromptText("Hours");
        wed1.setPrefWidth(tfw);
        Text t_wed_2 = new Text(":");
        final TextField wed2 = new TextField();
        wed2.setPromptText("Minutes");
        wed2.setPrefWidth(tfw);
        Text t_wed_3 = new Text(" - ");
        final TextField wed3 = new TextField();
        wed3.setPromptText("Hours");
        wed3.setPrefWidth(tfw);
        Text t_wed_4 = new Text(":");
        final TextField wed4 = new TextField();
        wed4.setPromptText("Minutes");
        wed4.setPrefWidth(tfw);
        Text t_wed_5 = new Text(" at ");
        final TextField wed5 = new TextField();
        wed5.setPromptText("Room");
        wed5.setPrefWidth(tfw);
        hb_wed.getChildren().add(wed);
        hb_wed.getChildren().add(t_wed_1);
        hb_wed.getChildren().add(wed1);
        hb_wed.getChildren().add(t_wed_2);
        hb_wed.getChildren().add(wed2);
        hb_wed.getChildren().add(t_wed_3);
        hb_wed.getChildren().add(wed3);
        hb_wed.getChildren().add(t_wed_4);
        hb_wed.getChildren().add(wed4);
        hb_wed.getChildren().add(t_wed_5);
        hb_wed.getChildren().add(wed5);
        //end Wednesday inputs
        //Thursday inputs
        HBox hb_thu = new HBox();
        final CheckBox thu = new CheckBox();
        Text t_thu_1 = new Text("Thursday:");
        final TextField thu1 = new TextField();
        thu1.setPromptText("Hours");
        thu1.setPrefWidth(tfw);
        Text t_thu_2 = new Text(":");
        final TextField thu2 = new TextField();
        thu2.setPromptText("Minutes");
        thu2.setPrefWidth(tfw);
        Text t_thu_3 = new Text(" - ");
        final TextField thu3 = new TextField();
        thu3.setPromptText("Hours");
        thu3.setPrefWidth(tfw);
        Text t_thu_4 = new Text(":");
        final TextField thu4 = new TextField();
        thu4.setPromptText("Minutes");
        thu4.setPrefWidth(tfw);
        Text t_thu_5 = new Text(" at ");
        final TextField thu5 = new TextField();
        thu5.setPromptText("Room");
        thu5.setPrefWidth(tfw);
        hb_thu.getChildren().add(thu);
        hb_thu.getChildren().add(t_thu_1);
        hb_thu.getChildren().add(thu1);
        hb_thu.getChildren().add(t_thu_2);
        hb_thu.getChildren().add(thu2);
        hb_thu.getChildren().add(t_thu_3);
        hb_thu.getChildren().add(thu3);
        hb_thu.getChildren().add(t_thu_4);
        hb_thu.getChildren().add(thu4);
        hb_thu.getChildren().add(t_thu_5);
        hb_thu.getChildren().add(thu5);
        //end Thursday inputs
        //Friday inputs
        HBox hb_fri = new HBox();
        final CheckBox fri = new CheckBox();
        Text t_fri_1 = new Text("Friday:");
        final TextField fri1 = new TextField();
        fri1.setPromptText("Hours");
        fri1.setPrefWidth(tfw);
        Text t_fri_2 = new Text(":");
        final TextField fri2 = new TextField();
        fri2.setPromptText("Minutes");
        fri2.setPrefWidth(tfw);
        Text t_fri_3 = new Text(" - ");
        final TextField fri3 = new TextField();
        fri3.setPromptText("Hours");
        fri3.setPrefWidth(tfw);
        Text t_fri_4 = new Text(":");
        final TextField fri4 = new TextField();
        fri4.setPromptText("Minutes");
        fri4.setPrefWidth(tfw);
        Text t_fri_5 = new Text(" at ");
        final TextField fri5 = new TextField();
        fri5.setPromptText("Room");
        fri5.setPrefWidth(tfw);
        hb_fri.getChildren().add(fri);
        hb_fri.getChildren().add(t_fri_1);
        hb_fri.getChildren().add(fri1);
        hb_fri.getChildren().add(t_fri_2);
        hb_fri.getChildren().add(fri2);
        hb_fri.getChildren().add(t_fri_3);
        hb_fri.getChildren().add(fri3);
        hb_fri.getChildren().add(t_fri_4);
        hb_fri.getChildren().add(fri4);
        hb_fri.getChildren().add(t_fri_5);
        hb_fri.getChildren().add(fri5);
        //end Friday inputs
        //Saturday inputs
        HBox hb_sat = new HBox();
        final CheckBox sat = new CheckBox();
        Text t_sat_1 = new Text("Saturday:");
        final TextField sat1 = new TextField();
        sat1.setPromptText("Hours");
        sat1.setPrefWidth(tfw);
        Text t_sat_2 = new Text(":");
        final TextField sat2 = new TextField();
        sat2.setPromptText("Minutes");
        sat2.setPrefWidth(tfw);
        Text t_sat_3 = new Text(" - ");
        final TextField sat3 = new TextField();
        sat3.setPromptText("Hours");
        sat3.setPrefWidth(tfw);
        Text t_sat_4 = new Text(":");
        final TextField sat4 = new TextField();
        sat4.setPromptText("Minutes");
        sat4.setPrefWidth(tfw);
        Text t_sat_5 = new Text(" at ");
        final TextField sat5 = new TextField();
        sat5.setPromptText("Room");
        sat5.setPrefWidth(tfw);
        hb_sat.getChildren().add(sat);
        hb_sat.getChildren().add(t_sat_1);
        hb_sat.getChildren().add(sat1);
        hb_sat.getChildren().add(t_sat_2);
        hb_sat.getChildren().add(sat2);
        hb_sat.getChildren().add(t_sat_3);
        hb_sat.getChildren().add(sat3);
        hb_sat.getChildren().add(t_sat_4);
        hb_sat.getChildren().add(sat4);
        hb_sat.getChildren().add(t_sat_5);
        hb_sat.getChildren().add(sat5);
        //end Saturday inputs
        final TextField messageField = new TextField();
        Button b2 = new Button("Add Meeting Time");
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Course temp = new Course();
                temp.setDescription(tf3.getText());
                Course c = courses.get(courses.indexOf(temp));
                Section m = new Section(tf5.getText());
                if(sun.isSelected()) {
                    short startHr = Short.parseShort(sun1.getText());
                    short startMin = Short.parseShort(sun2.getText());
                    short endHr = Short.parseShort(sun3.getText());
                    short endMin = Short.parseShort(sun4.getText());
                    Block b = new Block(Block.SUNDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),sun5.getText());
                    m.addBlock(b);
                }
                if(mon.isSelected()) {
                    short startHr = Short.parseShort(mon1.getText());
                    short startMin = Short.parseShort(mon2.getText());
                    short endHr = Short.parseShort(mon3.getText());
                    short endMin = Short.parseShort(mon4.getText());
                    Block b = new Block(Block.MONDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),mon5.getText());
                    m.addBlock(b);
                }
                if(tue.isSelected()) {
                    short startHr = Short.parseShort(tue1.getText());
                    short startMin = Short.parseShort(tue2.getText());
                    short endHr = Short.parseShort(tue3.getText());
                    short endMin = Short.parseShort(tue4.getText());
                    Block b = new Block(Block.TUESDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),tue5.getText());
                    m.addBlock(b);
                }
                if(wed.isSelected()) {
                    short startHr = Short.parseShort(wed1.getText());
                    short startMin = Short.parseShort(wed2.getText());
                    short endHr = Short.parseShort(wed3.getText());
                    short endMin = Short.parseShort(wed4.getText());
                    Block b = new Block(Block.WEDNESDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),wed5.getText());
                    m.addBlock(b);
                }
                if(thu.isSelected()) {
                    short startHr = Short.parseShort(thu1.getText());
                    short startMin = Short.parseShort(thu2.getText());
                    short endHr = Short.parseShort(thu3.getText());
                    short endMin = Short.parseShort(thu4.getText());
                    Block b = new Block(Block.THURSDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),thu5.getText());
                    m.addBlock(b);
                }
                if(fri.isSelected()) {
                    short startHr = Short.parseShort(fri1.getText());
                    short startMin = Short.parseShort(fri2.getText());
                    short endHr = Short.parseShort(fri3.getText());
                    short endMin = Short.parseShort(fri4.getText());
                    Block b = new Block(Block.FRIDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),fri5.getText());
                    m.addBlock(b);
                }
                if(sat.isSelected()) {
                    short startHr = Short.parseShort(sat1.getText());
                    short startMin = Short.parseShort(sat2.getText());
                    short endHr = Short.parseShort(sat3.getText());
                    short endMin = Short.parseShort(sat4.getText());
                    Block b = new Block(Block.SATURDAY,startHr,startMin,endHr,
                            endMin,tf4.getText(),sat5.getText());
                    m.addBlock(b);
                }
                c.addSection(m);
                messageField.setText("Added section successfully!");
            }
        });
        vb1.getChildren().add(tf3);
        vb1.getChildren().add(tf4);
        vb1.getChildren().add(tf5);
        vb1.getChildren().add(hb_sun);
        vb1.getChildren().add(hb_mon);
        vb1.getChildren().add(hb_tue);
        vb1.getChildren().add(hb_wed);
        vb1.getChildren().add(hb_thu);
        vb1.getChildren().add(hb_fri);
        vb1.getChildren().add(hb_sat);
        vb1.getChildren().add(b2);
        vb1.getChildren().add(messageField);
        
        Button b4 = new Button("Save Course/Section Info");
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                File f = fileChoose.showSaveDialog(primaryStage);
                if(f == null){
                    return;
                }
                try(java.io.PrintWriter p = new java.io.PrintWriter(f)){
                    p.println("Course,Number,Section,Instructor,Day,Start,End");
                    for(Course c : courses) {
                        for(Section s : c.getSections()) {
                            for(Block b : s.getBlocks()) {
                                p.println(c.getDescription()+","+
                                        c.getCourseNumber()+","+
                                        s.getSectionNumber()+","+
                                        b.instructor+","+
                                        b.day+","+
                                        (b.startTimeHours+b.startTimeMinutes/60f)+","+
                                        (b.endTimeHours+b.endTimeMinutes/60f));
                            }
                        }
                    }
                    p.flush();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ScheduleMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Button b5 = new Button("Load Course/Section Info");
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                File f = fileChoose.showOpenDialog(primaryStage);
                if(f == null) {
                    return;
                }
                courses.clear();
                try(java.io.BufferedReader buf = new java.io.BufferedReader(new java.io.FileReader(f))) {
                    String line = buf.readLine();
                    line = buf.readLine();
                    while(line != null && !line.equals("")) {
                        String[] values = line.split(",");
                        if(!values[0].equals("#")) {
                            Course c = new Course();
                            c.setDescription(values[0]);
                            c.setCourseNumber(values[1]);
                            if(courses.indexOf(c) >= 0){
                                c = courses.get(courses.indexOf(c));
                            } else {
                                courses.add(c);
                                System.out.println("adding course "+c.getDescription()+"; #="+courses.size());
                            }
                            Block b = new Block(Short.parseShort(values[4]), 
                                    Float.parseFloat(values[5]), 
                                    Float.parseFloat(values[6]),
                                    values[3], "");
                            c.addMeetingTime(values[2], b);
                        }
                        line = buf.readLine();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ScheduleMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Button b3 = new Button("Schedules!");
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Courses are:");
                for(Course c : courses) {
                    for(Section s: c.getSections()) {
                        System.out.println(c.getDescription());
                        System.out.println("    "+s.getTimeString());
                    }
                }
                scheds = Schedule.schedule(courses);
                try(java.io.PrintWriter p = new java.io.PrintWriter("schedules.txt")) {
                    int count = 1;
                    for(Schedule s: scheds) {
                        p.println("===================SCHEDULE #"+count+"========================");
                        for(Course c: s.getCourses()) {
                            Section sec = c.getSections().get(0);
                            p.println(c.getDescription());
                            p.println("    "+sec.getTimeString());
                        }
                        count++;
                    }
                    p.flush();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ScheduleMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
                ScheduleViewer sv = new ScheduleViewer(scheds);
                sv.show();
            }
        });
        
        ScrollPane root = new ScrollPane();
        VBox vb_parent = new VBox();
        vb_parent.setSpacing(25);
        vb_parent.getChildren().add(hb1);
        vb_parent.getChildren().add(vb1);
        vb_parent.getChildren().add(b4);
        vb_parent.getChildren().add(b5);
        vb_parent.getChildren().add(b3);
        root.setContent(vb_parent);
        
        Scene scene = new Scene(root, 600, 650);
        
        primaryStage.setTitle("ScheduleMaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
