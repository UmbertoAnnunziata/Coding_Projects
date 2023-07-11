import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.*;
/* Interior Design Capstone Project
Written in Java by Umberto Annunziata
8/22-11/22
 */
@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) throws IOException {
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //ArrayLists that will hold all information 
        ArrayList<JLabel> furn_Objects = new ArrayList<>();
        ArrayList<Integer> furn_Floor = new ArrayList<>();
        ArrayList<Integer> furn_Price = new ArrayList<>();
        ArrayList<JLabel> room_Objects = new ArrayList<>();
        ArrayList<Integer> room_Floor = new ArrayList<>();
        ArrayList<Integer> room_Price = new ArrayList<>();
        ArrayList<JLabel> door_Objects = new ArrayList<>();
        ArrayList<Integer> door_Floor = new ArrayList<>();
        ArrayList<Integer> door_Price = new ArrayList<>();
        ArrayList<JLabel> window_Objects = new ArrayList<>();
        ArrayList<Integer> window_Floor = new ArrayList<>();
        ArrayList<Integer> window_Price = new ArrayList<>();
        ArrayList<JLabel> stairs_Objects = new ArrayList<>();
        ArrayList<Integer> stairs_Floor = new ArrayList<>();
        ArrayList<Integer> stairs_Price = new ArrayList<>();
        ArrayList<JLabel> floor_Objects = new ArrayList<>();
        ArrayList<Integer> floor_Floor = new ArrayList<>();
        ArrayList<ImageIcon> allFurnImages = new ArrayList<>();

        //Variable Initialization
        BufferedImage myPicture;
        boolean bigger = false;
        int length = 0, width = 0;
        int floorNum = 0, floorClick = 1, button_Clicked = 0, loop = 0, lowerFloorW = 0, lowerFloorH = 0, index = 0;
        int i = 0, k = 0, d = 0, w = 0, s = 0, f = 0;
        int xtotalCostOffset = 0;
        int paintCost = 60, stairsMade = 1;
        String room;
        String furniture = "";
        String[] roomTypes = {"Bathroom", "Kitchen", "Living Room", "Bedroom", "Dining Room", "Closet"};
        String[] furnTypes = {"Couch", "Bed", "Sink", "Toilet",
                "Table", "Chair", "Shelves", "Cabinets",
                "Oven", "Washer", "Dryer", "Fridge"};

        final String[] formattedCost = {""};
        final int[] moving = {0};
        final int[] looped = {0};
        final int[] totalCost = {0};
        final int[] keyCodeEntered = {0};
        final int[] lastKeyCodeEntered = {2};
        final int[] rotateIndex = {0};
        final boolean[] pressedFurn = {false};
        final boolean[] pressedRoom = {false};
        final boolean[] pressedStair = {false};
        final boolean[] pressedWindow = {false};
        final boolean[] pressedDoor = {false};


        //Getting Dimensions for system running the program
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenSizeW =1280; //screenSize.width;
        int screenSizeH =720;//screenSize.height;
        //out.println("Height: " + screenSizeH + "\n" + "Width: " + screenSizeW);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

        //Window Creation, and makes it visible at startup of program
        JFrame window = new JFrame("Interior Design Software (IDS)");
        window.setSize(screenSizeW, screenSizeH);
        Container contentPane = window.getContentPane();
        contentPane.setLayout(null);
        window.getContentPane().setBackground(Color.WHITE);
        window.setFocusable(true);
        JLayeredPane layers = new JLayeredPane();
        window.add(layers);
        layers.setBounds(0,0,screenSizeW,screenSizeH);
        layers.setFocusable(true);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

        //Trash Can
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\TrashCan.png"));
        Image trash = myPicture.getScaledInstance(20,30, Image.SCALE_SMOOTH);
        JLabel Trash = new JLabel(new ImageIcon(trash));

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Creation of Window Messages, hidden until needed
        Font font = new Font("Arial", Font.BOLD, 15);
        JLabel DW_message = new JLabel("Length",JLabel.CENTER);
        DW_message.setFont(font);
        JLabel width_message = new JLabel(" Width ", JLabel.CENTER);
        width_message.setFont(font);
        JLabel length_message = new JLabel(" Length ", JLabel.CENTER);
        length_message.setFont(font);
        JLabel room_message = new JLabel("Room Type", JLabel.CENTER);
        room_message.setFont(font);
        JLabel furn_message = new JLabel("Furniture Type", JLabel.CENTER);
        furn_message.setFont(font);
        JLabel houseCost = new JLabel("Estimated House Cost: $" + totalCost[0], JLabel.CENTER);
        houseCost.setFont(font);
        JLabel floor_dimension = new JLabel("Floor Dimensions", JLabel.CENTER);
        floor_dimension.setFont(font);
        JLabel stair_dimension = new JLabel("Stair Dimensions", JLabel.CENTER);
        stair_dimension.setFont(font);
        JLabel stair_Num = new JLabel("Floor #" + floorClick);
        stair_Num.setFont(font);
        JLabel item_Pressed = new JLabel("Test");
        item_Pressed.setFont(font);



        //Text field to write in inputs
        JTextField write = new JTextField();
        write.setBounds(50, 310, 200, 50);
        write.setText(String.valueOf(0));
        JTextField write2 = new JTextField();
        write2.setBounds(50, 360, 200, 50);
        write2.setText(String.valueOf(0));
        JTextField DW_write = new JTextField();
        DW_write.setBounds(50,310,200,50);
        DW_write.setText(String.valueOf(0));
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Dropdown Menu for room types (Includes ActionListener)
        JComboBox<String> dropMenu = new JComboBox<>(roomTypes);
        dropMenu.setBounds(100, 100, 200, 50);
        dropMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        //Dropdown menu for furniture types (Includes ActionListener)
        JComboBox<String> furnDropMenu = new JComboBox<>(furnTypes);
        furnDropMenu.setBounds(100, 100, 200, 50);
        furnDropMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Creates border to make the floor plan
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Border.png"));
        Image border = myPicture.getScaledInstance(10, screenSizeH, Image.SCALE_SMOOTH);
        JLabel right_Border_Image = new JLabel(new ImageIcon(border));
        JLabel sideButton_Image = new JLabel(new ImageIcon(border));
        border = myPicture.getScaledInstance(screenSizeW - 290, 10, Image.SCALE_SMOOTH);
        JLabel overButton_Image = new JLabel(new ImageIcon(border));
        JLabel underButton_Image = new JLabel(new ImageIcon(border));

        right_Border_Image.setBounds(screenSizeW - 280, 15, 5, screenSizeH);
        overButton_Image.setBounds(screenSizeW-280,15,225,5);
        underButton_Image.setBounds(screenSizeW-280,screenSizeH-110,300,5);
        sideButton_Image.setBounds(screenSizeW-60,15,5,screenSizeH - 120);

        window.add(right_Border_Image);
        right_Border_Image.setVisible(true);
        window.add(underButton_Image);
        underButton_Image.setVisible(true);
        window.add(overButton_Image);
        overButton_Image.setVisible(true);
        window.add(sideButton_Image);
        sideButton_Image.setVisible(true);

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        //Movement and Rotation for all objects that can be placed
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                //Rotating Furniture
                //Up
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if (keyCode == 87 || keyCode == 38) {
                    out.println("Up");
                    keyCodeEntered[0] = 1;
                }
                //Down
                if (keyCode == 83 || keyCode == 40) {
                    out.println("Down");
                    keyCodeEntered[0] = 2;
                }
                //Left
                if (keyCode == 65 || keyCode == 37) {
                    out.println("Left");
                    keyCodeEntered[0] = 3;
                }
                //Right
                if (keyCode == 68 || keyCode == 39) {
                    out.println("Right");
                    keyCodeEntered[0] = 4;

                }
            }
            @Override
            public void keyReleased(KeyEvent e) { }
        });

        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                for(looped[0] = 0; looped[0] < furn_Objects.size(); looped[0]++) {
                    if (e.getY() <= furn_Objects.get(looped[0]).getY() + furn_Objects.get(looped[0]).getPreferredSize().getHeight() + 5 && e.getY() >= furn_Objects.get(looped[0]).getY() - 5) {
                        if (e.getX() <= furn_Objects.get(looped[0]).getX() + furn_Objects.get(looped[0]).getPreferredSize().getWidth() + 5 && e.getX() >= furn_Objects.get(looped[0]).getX() - 5) {
                            if(furn_Objects.get(looped[0]).isVisible()) {
                                pressedFurn[0] = true;
                                moving[0] = looped[0];
                                out.println(e.getX() + " " + e.getY());
                            }
                        }
                    }
                }
                for(looped[0] = 0; looped[0] < window_Objects.size(); looped[0]++) {
                    if (e.getY() <= window_Objects.get(looped[0]).getY() + window_Objects.get(looped[0]).getPreferredSize().getHeight() + 10 && e.getY() >= window_Objects.get(looped[0]).getY() - 10) {
                        if(e.getX() <= window_Objects.get(looped[0]).getX() + window_Objects.get(looped[0]).getPreferredSize().getWidth() + 10 && e.getX() >= window_Objects.get(looped[0]).getX() - 10) {
                            if(window_Objects.get(looped[0]).isVisible()){
                                pressedWindow[0] = true;
                                moving[0] = looped[0];
                                out.println(e.getX() + " " + e.getY());
                            }
                        }
                    }
                }
                for(looped[0] = 0; looped[0] < door_Objects.size(); looped[0]++) {
                    if (e.getY() <= door_Objects.get(looped[0]).getY() + door_Objects.get(looped[0]).getPreferredSize().getHeight() + 10 && e.getY() >= door_Objects.get(looped[0]).getY() - 10) {
                        if(e.getX() <= door_Objects.get(looped[0]).getX() + door_Objects.get(looped[0]).getPreferredSize().getWidth() + 10 && e.getX() >= door_Objects.get(looped[0]).getX() - 10) {
                            if(door_Objects.get(looped[0]).isVisible()) {
                                pressedDoor[0] = true;
                                moving[0] = looped[0];
                                out.println(e.getX() + " " + e.getY());
                            }
                        }
                    }
                }
                for(looped[0] = 0; looped[0] < stairs_Objects.size(); looped[0]++) {
                    if (e.getY() <= stairs_Objects.get(looped[0]).getY() + stairs_Objects.get(looped[0]).getPreferredSize().getHeight() + 5 && e.getY() >= stairs_Objects.get(looped[0]).getY() - 5) {
                        if (e.getX() <= stairs_Objects.get(looped[0]).getX() + stairs_Objects.get(looped[0]).getPreferredSize().getWidth() + 5 && e.getX() >= stairs_Objects.get(looped[0]).getX() - 5) {
                            if(stairs_Objects.get(looped[0]).isVisible()) {
                                pressedStair[0] = true;
                                moving[0] = looped[0];
                                out.println(e.getX() + " " + e.getY());
                            }
                        }
                    }
                }
                for(looped[0] = 0; looped[0] < room_Objects.size(); looped[0]++) {
                    if (e.getY() <= room_Objects.get(looped[0]).getY() + room_Objects.get(looped[0]).getPreferredSize().getHeight() + 5 && e.getY() >= room_Objects.get(looped[0]).getY() - 5) {
                        if (e.getX() <= room_Objects.get(looped[0]).getX() + room_Objects.get(looped[0]).getPreferredSize().getWidth() + 5 && e.getX() >= room_Objects.get(looped[0]).getX() - 5) {
                            if (room_Objects.get(looped[0]).isVisible()){
                                //Only runs if nothing else was clicked on (Prevents collision issues)
                                if (!pressedFurn[0] && !pressedWindow[0] && !pressedDoor[0] && !pressedStair[0]) {
                                    pressedRoom[0] = true;
                                    moving[0] = looped[0];
                                    out.println(e.getX() + " " + e.getY());
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Released");
                if(pressedFurn[0]) {
                    out.println("Moved");
                    out.println(e.getX() + " " + e.getY());
                    furn_Objects.get(moving[0]).setLocation(e.getX() - (int) (furn_Objects.get(moving[0]).getPreferredSize().getWidth() / 2), e.getY() - (int) furn_Objects.get(moving[0]).getPreferredSize().getHeight() / 2);
                    pressedFurn[0] = false;

                    if (keyCodeEntered[0] > 0){
                        for(looped[0] = 0; looped[0] < allFurnImages.size(); looped[0]++) {
                            if (furn_Objects.get(moving[0]).getIcon().equals(allFurnImages.get(looped[0]))) {
                                out.println("Match at " + looped[0] + "Position");
                                rotateIndex[0] = (looped[0]/4) * 4;
                                out.println(rotateIndex[0]);
                            }
                        }
                        //Rotating Furniture Dimensions
                        if (keyCodeEntered[0] == 1) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                furn_Objects.get(moving[0]).setSize((int)furn_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)furn_Objects.get(moving[0]).getPreferredSize().getWidth());
                                out.println((int)furn_Objects.get(moving[0]).getPreferredSize().getWidth() + " " + (int)furn_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            out.println("Works");
                            furn_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 3));
                            lastKeyCodeEntered[0] = 1;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 2) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                furn_Objects.get(moving[0]).setSize((int)furn_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)furn_Objects.get(moving[0]).getPreferredSize().getWidth());
                                System.out.println((int)furn_Objects.get(moving[0]).getPreferredSize().getHeight() + " " + (int)furn_Objects.get(moving[0]).getPreferredSize().getWidth());
                            }
                            out.println("Works");
                            furn_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0]));
                            lastKeyCodeEntered[0] = 2;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 3) {
                            out.println("Works");
                            furn_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 1));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                furn_Objects.get(moving[0]).setSize((int)furn_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)furn_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 3;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 4) {
                            out.println("Works");
                            furn_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 2));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                furn_Objects.get(moving[0]).setSize((int)furn_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)furn_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 4;
                            keyCodeEntered[0] = 0;
                        }
                    }
                    //Moving Furns to Trash
                    if (Trash.getY() <= furn_Objects.get(moving[0]).getY() + furn_Objects.get(moving[0]).getPreferredSize().getHeight() + 5 && Trash.getY() >= furn_Objects.get(moving[0]).getY() - 5) {
                        if (Trash.getX() <= furn_Objects.get(moving[0]).getX() + furn_Objects.get(moving[0]).getPreferredSize().getWidth() + 5 && Trash.getX() >= furn_Objects.get(moving[0]).getX() - 5) {
                            furn_Floor.set(moving[0],99);
                            furn_Objects.get(moving[0]).setVisible(false);
                            totalCost[0] = totalCost[0] - furn_Price.get(moving[0]);
                        }
                    }
                }
                if(pressedRoom[0]){
                    out.println("Moved");
                    out.println(e.getX() + " " + e.getY());
                    room_Objects.get(moving[0]).setLocation(e.getX()-(int)(room_Objects.get(moving[0]).getPreferredSize().getWidth()/2),e.getY()-(int)(room_Objects.get(moving[0]).getPreferredSize().getHeight()/2));
                    pressedRoom[0] = false;

                    if (keyCodeEntered[0] > 0){
                        for(looped[0] = 0; looped[0] < allFurnImages.size(); looped[0]++) {
                            if (room_Objects.get(moving[0]).getIcon().equals(allFurnImages.get(looped[0]))) {
                                out.println("Match at " + looped[0] + "Position");
                                rotateIndex[0] = (looped[0]/4) * 4;
                                out.println(rotateIndex[0]);
                            }
                        }
                        //Rotating Room Dimensions
                        if (keyCodeEntered[0] == 1) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                room_Objects.get(moving[0]).setSize((int)room_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)room_Objects.get(moving[0]).getPreferredSize().getWidth());
                                out.println((int)room_Objects.get(moving[0]).getPreferredSize().getWidth() + " " + (int)room_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            out.println("Works");
                            room_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 3));
                            lastKeyCodeEntered[0] = 1;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 2) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                room_Objects.get(moving[0]).setSize((int)room_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)room_Objects.get(moving[0]).getPreferredSize().getWidth());
                            }
                            out.println("Works");
                            room_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0]));
                            lastKeyCodeEntered[0] = 2;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 3) {
                            out.println("Works");
                            room_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 1));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                room_Objects.get(moving[0]).setSize((int)room_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)room_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 3;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 4) {
                            out.println("Works");
                            room_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 2));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                room_Objects.get(moving[0]).setSize((int)room_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)room_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 4;
                            keyCodeEntered[0] = 0;
                        }
                    }

                    //Moving Rooms to Trash
                    if (Trash.getY() <= room_Objects.get(moving[0]).getY() + room_Objects.get(moving[0]).getPreferredSize().getHeight() + 5 && Trash.getY() >= room_Objects.get(moving[0]).getY() - 5) {
                        if (Trash.getX() <= room_Objects.get(moving[0]).getX() + room_Objects.get(moving[0]).getPreferredSize().getWidth() + 5 && Trash.getX() >= room_Objects.get(moving[0]).getX() - 5) {
                            room_Floor.set(moving[0],99);
                            room_Objects.get(moving[0]).setVisible(false);
                            totalCost[0] = totalCost[0] - room_Price.get(moving[0]);
                        }
                    }
                }
                if(pressedWindow[0]){
                    out.println("Moved");
                    out.println(e.getX() + " " + e.getY());
                    window_Objects.get(moving[0]).setLocation(e.getX()-(int)(window_Objects.get(moving[0]).getPreferredSize().getWidth()/2),e.getY()-(int) window_Objects.get(moving[0]).getPreferredSize().getHeight()/2);
                    pressedWindow[0] = false;

                    if (keyCodeEntered[0] > 0){
                        for(looped[0] = 0; looped[0] < allFurnImages.size(); looped[0]++) {
                            if (window_Objects.get(moving[0]).getIcon().equals(allFurnImages.get(looped[0]))) {
                                out.println("Match at " + looped[0] + "Position");
                                rotateIndex[0] = (looped[0]/4) * 4;
                                out.println(rotateIndex[0]);
                            }
                        }
                        //Rotating Furniture Dimensions
                        if (keyCodeEntered[0] == 1) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                window_Objects.get(moving[0]).setSize((int)window_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)window_Objects.get(moving[0]).getPreferredSize().getWidth());
                                out.println((int)window_Objects.get(moving[0]).getPreferredSize().getWidth() + " " + (int)window_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            out.println("Works");
                            window_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 3));
                            lastKeyCodeEntered[0] = 1;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 2) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                window_Objects.get(moving[0]).setSize((int)window_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)window_Objects.get(moving[0]).getPreferredSize().getWidth());
                            }
                            out.println("Works");
                            window_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0]));
                            lastKeyCodeEntered[0] = 2;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 3) {
                            out.println("Works");
                            window_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 1));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                window_Objects.get(moving[0]).setSize((int)window_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)window_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 3;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 4) {
                            out.println("Works");
                            window_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 2));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                window_Objects.get(moving[0]).setSize((int)window_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)window_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 4;
                            keyCodeEntered[0] = 0;
                        }
                    }

                    //Moving Windows to Trash
                    if (Trash.getY() <= window_Objects.get(moving[0]).getY() + window_Objects.get(moving[0]).getPreferredSize().getHeight() + 5 && Trash.getY() >= window_Objects.get(moving[0]).getY() - 5) {
                        if (Trash.getX() <= window_Objects.get(moving[0]).getX() + window_Objects.get(moving[0]).getPreferredSize().getWidth() + 5 && Trash.getX() >= window_Objects.get(moving[0]).getX() - 5) {
                            window_Floor.set(moving[0],99);
                            window_Objects.get(moving[0]).setVisible(false);
                            totalCost[0] = totalCost[0] - window_Price.get(moving[0]);
                        }
                    }
                }

                if(pressedDoor[0]){
                    out.println("Moved");
                    out.println(e.getX() + " " + e.getY());
                    door_Objects.get(moving[0]).setLocation(e.getX()-(int)(door_Objects.get(moving[0]).getPreferredSize().getWidth()/2),e.getY()-(int) door_Objects.get(moving[0]).getPreferredSize().getHeight()/2);
                    pressedDoor[0] = false;

                    if (keyCodeEntered[0] > 0){
                        for(looped[0] = 0; looped[0] < allFurnImages.size(); looped[0]++) {
                            if (door_Objects.get(moving[0]).getIcon().equals(allFurnImages.get(looped[0]))) {
                                out.println("Match at " + looped[0] + "Position");
                                rotateIndex[0] = (looped[0]/4) * 4;
                                out.println(rotateIndex[0]);
                            }
                        }
                        //Rotating Furniture Dimensions
                        if (keyCodeEntered[0] == 1) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                door_Objects.get(moving[0]).setSize((int)door_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)door_Objects.get(moving[0]).getPreferredSize().getWidth());
                                out.println((int)door_Objects.get(moving[0]).getPreferredSize().getWidth() + " " + (int)door_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            out.println("Works");
                            door_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 3));
                            lastKeyCodeEntered[0] = 1;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 2) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                door_Objects.get(moving[0]).setSize((int)door_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)door_Objects.get(moving[0]).getPreferredSize().getWidth());
                            }
                            out.println("Works");
                            door_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0]));
                            lastKeyCodeEntered[0] = 2;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 3) {
                            out.println("Works");
                            door_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 1));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                door_Objects.get(moving[0]).setSize((int)door_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)door_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 3;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 4) {
                            out.println("Works");
                            door_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 2));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                door_Objects.get(moving[0]).setSize((int)door_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)door_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 4;
                            keyCodeEntered[0] = 0;
                        }
                    }

                    //Moving Doors to Trash
                    if (Trash.getY() <= door_Objects.get(moving[0]).getY() + door_Objects.get(moving[0]).getPreferredSize().getHeight() + 5 && Trash.getY() >= door_Objects.get(moving[0]).getY() - 5) {
                        if (Trash.getX() <= door_Objects.get(moving[0]).getX() + door_Objects.get(moving[0]).getPreferredSize().getWidth() + 5 && Trash.getX() >= door_Objects.get(moving[0]).getX() - 5) {
                            door_Floor.set(moving[0],99);
                            door_Objects.get(moving[0]).setVisible(false);
                            totalCost[0] = totalCost[0] - door_Price.get(moving[0]);
                        }
                    }
                }

                if(pressedStair[0]){
                    out.println("Moved");
                    out.println(e.getX() + " " + e.getY());
                    stairs_Objects.get(moving[0]).setLocation(e.getX()-(int)(stairs_Objects.get(moving[0]).getPreferredSize().getWidth()/2),e.getY()-(int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight()/2);
                    pressedStair[0] = false;

                    if (keyCodeEntered[0] > 0){
                        for(looped[0] = 0; looped[0] < allFurnImages.size(); looped[0]++) {
                            if (stairs_Objects.get(moving[0]).getIcon().equals(allFurnImages.get(looped[0]))) {
                                out.println("Match at " + looped[0] + "Position");
                                rotateIndex[0] = (looped[0]/4) * 4;
                                out.println(rotateIndex[0]);
                            }
                        }
                        //Rotating Furniture Dimensions
                        if (keyCodeEntered[0] == 1) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                stairs_Objects.get(moving[0]).setSize((int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)stairs_Objects.get(moving[0]).getPreferredSize().getWidth());
                                out.println((int)stairs_Objects.get(moving[0]).getPreferredSize().getWidth() + " " + (int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            out.println("Works");
                            stairs_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 3));
                            lastKeyCodeEntered[0] = 1;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 2) {
                            if (lastKeyCodeEntered[0] == 3 || lastKeyCodeEntered[0] == 4) {
                                stairs_Objects.get(moving[0]).setSize((int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight(), (int)stairs_Objects.get(moving[0]).getPreferredSize().getWidth());
                            }
                            out.println("Works");
                            stairs_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0]));
                            lastKeyCodeEntered[0] = 2;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 3) {
                            out.println("Works");
                            stairs_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 1));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                stairs_Objects.get(moving[0]).setSize((int)stairs_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 3;
                            keyCodeEntered[0] = 0;
                        }
                        if (keyCodeEntered[0] == 4) {
                            out.println("Works");
                            stairs_Objects.get(moving[0]).setIcon(allFurnImages.get(rotateIndex[0] + 2));
                            if (lastKeyCodeEntered[0] == 1 || lastKeyCodeEntered[0] == 2) {
                                stairs_Objects.get(moving[0]).setSize((int)stairs_Objects.get(moving[0]).getPreferredSize().getWidth(), (int)stairs_Objects.get(moving[0]).getPreferredSize().getHeight());
                            }
                            lastKeyCodeEntered[0] = 4;
                            keyCodeEntered[0] = 0;
                        }
                    }

                    //Moving stairs to Trash
                    /*
                    if (Trash.getY() <= stairs_Objects.get(moving[0]).getY() + stairs_Objects.get(moving[0]).getPreferredSize().getHeight() + 5 && Trash.getY() >= stairs_Objects.get(moving[0]).getY() - 5) {
                        if (Trash.getX() <= stairs_Objects.get(moving[0]).getX() + stairs_Objects.get(moving[0]).getPreferredSize().getWidth() + 5 && Trash.getX() >= stairs_Objects.get(moving[0]).getX() - 5) {
                            stairs_Floor.set(moving[0],99);
                            stairs_Objects.get(moving[0]).setVisible(false);
                            totalCost[0] = totalCost[0] - stairs_Price.get(moving[0]);
                        }
                    }*/
                }
                formattedCost[0] = String.format("%,d", totalCost[0]);
                houseCost.setText(String.valueOf("Estimated House Cost: $" + totalCost[0]));
                if(keyCodeEntered[0] != 0){
                    keyCodeEntered[0] = 0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        //Button creation
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\AddR.png"));
        Image addR = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click = new JButton(new ImageIcon(addR));
        click.setBounds(50, 100, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\AddFloor.png"));
        Image addFloor = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click2 = new JButton(new ImageIcon(addFloor));
        click2.setBounds(50, 150, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\AddW.png"));
        Image addW = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click3 = new JButton(new ImageIcon(addW));
        click3.setBounds(50, 200, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\AddD.png"));
        Image addD = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click4 = new JButton(new ImageIcon(addD));
        click4.setBounds(50, 250, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Confirm.png"));
        Image confirm = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click5 = new JButton(new ImageIcon(confirm));
        click5.setBounds(50, 320, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\AddF.png"));
        Image addF = myPicture.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        JButton click6 = new JButton(new ImageIcon(addF));
        click6.setBounds(50, 370, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\downF.png"));
        Image downF = myPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton downFloor = new JButton(new ImageIcon(downF));
        downFloor.setBounds(50, 370, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\upF.png"));
        Image upF = myPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton upFloor = new JButton(new ImageIcon(upF));
        upFloor.setBounds(50, 370, 200, 50);
        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Cancel.png"));
        Image cancel = myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton Cancel = new JButton(new ImageIcon(cancel));
        Cancel.setBounds(50,370,20,20);

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Adds all assets to the window, hides the ones that aren't used immediately
        window.add(write);
        write.setVisible(false);
        window.add(write2);
        write2.setVisible(false);
        window.add(DW_write);
        DW_write.setVisible(false);
        window.add(Trash);
        layers.add(Trash, new Integer(2));
        window.add(click);
        window.add(click2);
        window.add(click3);
        window.add(click4);
        window.add(click5);
        window.add(click6);
        window.add(upFloor);
        window.add(downFloor);
        window.add(Cancel);
        click5.setVisible(false);
        Cancel.setVisible(false);
        window.add(dropMenu);
        dropMenu.setVisible(false);
        window.add(furnDropMenu);
        furnDropMenu.setVisible(false);
        window.setVisible(true);
        window.add(room_message);
        room_message.setVisible(false);
        window.add(length_message);
        length_message.setVisible(false);
        window.add(width_message);
        width_message.setVisible(false);
        window.add(furn_message);
        furn_message.setVisible(false);
        window.add(houseCost);
        houseCost.setVisible(true);
        window.add(DW_message);
        DW_message.setVisible(false);
        window.add(floor_dimension);
        floor_dimension.setVisible(false);
        window.add(stair_dimension);
        stair_dimension.setVisible(false);
        //window.add(stair_Num);
        layers.add(stair_Num, new Integer(2));
        layers.add(item_Pressed, new Integer(2));
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Places all assets on their respective positions on the window
        double wide = window.getSize().getWidth();
        double tall = window.getSize().getHeight();
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //Runtime Manipulation
        while (1 > 0) {

            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            click.setBounds((int) wide / 2 + 375, (int) tall / 2 - 290, 200, 50);
            click2.setBounds((int) wide / 2 + 375, (int) tall / 2 - 340, 200, 50);
            click3.setBounds((int) wide / 2 + 375, (int) tall / 2 - 140, 200, 50);
            click4.setBounds((int) wide / 2 + 375, (int) tall / 2 - 240, 200, 50);
            click5.setBounds((int) wide / 2 + 375, (int) tall / 2 + 150, 200, 50);
            click6.setBounds((int) wide / 2 + 375, (int) tall / 2 - 190, 200, 50);
            upFloor.setBounds((int) wide / 2 + 480, (int) tall - 150, 40, 40);
            downFloor.setBounds((int) wide / 2 + 420, (int) tall - 150, 40, 40);
            Cancel.setBounds((int) wide / 2 + 550, (int) tall - 150, 20, 20);
            write.setBounds((int) wide / 2 + 375, (int) tall / 2 - 70, 200, 50);
            DW_write.setBounds((int) wide / 2 + 375, (int) tall / 2 - 70, 200, 50);
            write2.setBounds((int) wide / 2 + 375, (int) tall / 2 + 10, 200, 50);
            dropMenu.setBounds((int) wide / 2 + 375, (int) tall / 2 + 90, 200, 50);
            furnDropMenu.setBounds((int) wide / 2 + 375, (int) tall / 2 + 90, 200, 50);
            houseCost.setBounds((int) wide / 2 + xtotalCostOffset - 40, screenSizeH-120, 1000, 50);
            width_message.setBounds((int) wide / 2 + 375, (int) tall / 2 - 105, 200, 50);
            DW_message.setBounds((int) wide / 2 + 375, (int) tall / 2 - 105, 200, 50);
            length_message.setBounds((int) wide / 2 + 375, (int) tall / 2 - 30, 200, 50);
            room_message.setBounds((int) wide / 2 + 375, (int) tall / 2 + 50, 200, 50);
            furn_message.setBounds((int) wide / 2 + 375, (int) tall / 2 + 50, 200, 50);
            floor_dimension.setBounds((int) wide / 2 + 375, (int) tall / 2 + 45, 200, 50);
            stair_dimension.setBounds((int) wide / 2 + 375, (int) tall / 2 + 45, 200, 50);
            stair_Num.setBounds(10, 15, 200, 50);
            Trash.setBounds(30,0,20,30);
            item_Pressed.setBounds(10,30,200,50);


            //Tests to see what is pressed on screen
            if(pressedFurn[0]){
                item_Pressed.setText("Furniture Pressed");
            }
            else if(pressedDoor[0]){
                item_Pressed.setText("Door Pressed");
            }
            else if(pressedRoom[0]){
                item_Pressed.setText("Room Pressed");
            }
            else if(pressedWindow[0]){
                item_Pressed.setText("Window Pressed");
            }
            else if(pressedStair[0]){
                item_Pressed.setText("Stair Pressed");
            }
            else if(!pressedFurn[0] && !pressedDoor[0] && !pressedRoom[0] && !pressedWindow[0] && !pressedStair[0]){
                item_Pressed.setText("");
            }

            //Brings objects to the screen when certain buttons are pressed
            //Room Button
            if (click.getModel().isPressed()) {
                if (Cancel.isVisible() == false && floor_Floor.size() > 0) {
                    //out.println("Pressed");
                    write.setVisible(true);
                    write2.setVisible(true);
                    click5.setVisible(true);
                    Cancel.setVisible(true);
                    dropMenu.setVisible(true);
                    width_message.setVisible(true);
                    length_message.setVisible(true);
                    room_message.setVisible(true);
                    button_Clicked = 1;
                }
            }
            //Window Button
            if (click3.getModel().isPressed()) {
                if (Cancel.isVisible() == false && floor_Floor.size() > 0) {
                    DW_write.setVisible(true);
                    DW_message.setVisible(true);
                    click5.setVisible(true);
                    Cancel.setVisible(true);
                    button_Clicked = 2;
                }
            }
            //Door Button
            if (click4.getModel().isPressed()) {
                if (Cancel.isVisible() == false && floor_Floor.size() > 0) {
                    DW_write.setVisible(true);
                    DW_message.setVisible(true);
                    click5.setVisible(true);
                    Cancel.setVisible(true);
                    button_Clicked = 3;
                }
            }
            //Floor Button
            if (click2.getModel().isPressed() && (floorClick == floorNum || floorNum == 0)) {
                if (Cancel.isVisible() == false) {
                    write.setVisible(true);
                    write2.setVisible(true);
                    click5.setVisible(true);
                    Cancel.setVisible(true);
                    width_message.setVisible(true);
                    length_message.setVisible(true);
                    floor_dimension.setVisible(true);
                    button_Clicked = 4;
                }
            }
            //Furniture Button
            if (click6.getModel().isPressed()) {
                if (Cancel.isVisible() == false && floor_Floor.size() > 0) {
                    //out.println("Pressed");
                    write.setVisible(true);
                    write2.setVisible(true);
                    click5.setVisible(true);
                    Cancel.setVisible(true);
                    width_message.setVisible(true);
                    length_message.setVisible(true);
                    furnDropMenu.setVisible(true);
                    furn_message.setVisible(true);
                    button_Clicked = 5;
                }
            }

            if (Cancel.getModel().isPressed() && stairsMade != 0) {
                Cancel.setVisible(false);
                write.setVisible(false);
                write2.setVisible(false);
                click5.setVisible(false);
                room_message.setVisible(false);
                dropMenu.setVisible(false);
                width_message.setVisible(false);
                length_message.setVisible(false);
                furnDropMenu.setVisible(false);
                furn_message.setVisible(false);
                DW_write.setVisible(false);
                DW_message.setVisible(false);
                floor_dimension.setVisible(false);
                stair_dimension.setVisible(false);
            }

            if (upFloor.getModel().isPressed() || downFloor.getModel().isPressed()) {
                //Up Floor Button
                if (upFloor.getModel().isPressed()) {
                    if (floorClick < floorNum) {
                        upFloor.setEnabled(false);
                        floorClick++;
                        out.println(floorClick);
                    }
                }
                //Down Floor Button
                if (downFloor.getModel().isPressed()) {
                    if (floorClick > 1) {
                        downFloor.setEnabled(false);
                        floorClick--;
                        out.println(floorClick);
                    }
                }

                upFloor.setEnabled(true);
                downFloor.setEnabled(true);

                //When the floor is changed, the furniture for that floor is shown, the rest is hidden
                for (loop = 0; loop < furn_Floor.size(); loop++) {
                    if (furn_Floor.get(loop) != floorClick) {
                        furn_Objects.get(loop).setVisible(false);
                    }
                    if (furn_Floor.get(loop) == floorClick) {
                        furn_Objects.get(loop).setVisible(true);
                    }
                }
                for (loop = 0; loop < door_Floor.size(); loop++) {
                    if (door_Floor.get(loop) != floorClick) {
                        door_Objects.get(loop).setVisible(false);
                    }
                    if (door_Floor.get(loop) == floorClick) {
                        door_Objects.get(loop).setVisible(true);
                    }
                }
                for (loop = 0; loop < window_Floor.size(); loop++) {
                    if (window_Floor.get(loop) != floorClick) {
                        window_Objects.get(loop).setVisible(false);
                    }
                    if (window_Floor.get(loop) == floorClick) {
                        window_Objects.get(loop).setVisible(true);
                    }
                }
                for (loop = 0; loop < room_Floor.size(); loop++) {
                    if (room_Floor.get(loop) != floorClick) {
                        room_Objects.get(loop).setVisible(false);
                    }
                    if (room_Floor.get(loop) == floorClick) {
                        room_Objects.get(loop).setVisible(true);
                    }
                }
                for (loop = 0; loop < stairs_Floor.size(); loop++) {
                    if (stairs_Floor.get(loop) != floorClick) {
                        stairs_Objects.get(loop).setVisible(false);
                    }
                    if (stairs_Floor.get(loop) == floorClick || stairs_Floor.get(loop) + 1 == floorClick) {
                        stairs_Objects.get(loop).setVisible(true);
                    }
                }
                for (loop = 0; loop < floor_Floor.size(); loop++) {
                    if (floor_Floor.get(loop) != floorClick) {
                        floor_Objects.get(loop).setVisible(false);
                    }
                    if (floor_Floor.get(loop) == floorClick) {
                        floor_Objects.get(loop).setVisible(true);
                    }
                }
            }


            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //Stores info put into the text boxes into the proper object, and resets the screen
            //Room Input
            if (button_Clicked == 1) {
                if (click5.getModel().isPressed()) {
                    if (Integer.parseInt(write.getText()) > 0 && Integer.parseInt(write2.getText()) > 0) {
                        click5.setEnabled(false);
                        out.println("Confirm is Pressed");
                        width = Integer.parseInt(write.getText()) * 12 * 2;
                        length = Integer.parseInt(write2.getText()) * 12 * 2;
                        room = dropMenu.getSelectedItem().toString();
                        switch (room) {
                            case "Bathroom":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bathroom.png"));
                                Image bathroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bathroom));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bathroom.png"));
                                bathroom = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bathroom));
                                allFurnImages.add(new ImageIcon(bathroom));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bathroom.png"));
                                bathroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bathroom));

                                room_Floor.add(floorClick);
                                //Price includes estimated flooring and paint cost
                                room_Price.add(((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));
                                totalCost[0] += (((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            case "Kitchen":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Kitchen.png"));
                                Image kitchen = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(kitchen));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Kitchen.png"));
                                kitchen = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(kitchen));
                                allFurnImages.add(new ImageIcon(kitchen));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Kitchen.png"));
                                kitchen = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(kitchen));

                                room_Floor.add(floorClick);
                                room_Price.add(((length/24) * (width/24) * 20) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));
                                totalCost[0] += (((length/24) * (width/24) * 20) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            case "Living Room":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\LivingRoom.png"));
                                Image livingroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\LivingRoom.png"));
                                livingroom = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));
                                allFurnImages.add(new ImageIcon(livingroom));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\LivingRoom.png"));
                                livingroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));

                                room_Floor.add(floorClick);
                                room_Price.add(((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));
                                totalCost[0] += (((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            case "Bedroom":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bedroom.png"));
                                Image bedroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bedroom));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bedroom.png"));
                                bedroom = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bedroom));
                                allFurnImages.add(new ImageIcon(bedroom));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bedroom.png"));
                                bedroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bedroom));

                                room_Floor.add(floorClick);
                                room_Price.add(((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                totalCost[0] += (((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            case "Dining Room":
                                livingroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                livingroom = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));
                                allFurnImages.add(new ImageIcon(livingroom));
                                livingroom = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(livingroom));

                                room_Floor.add(floorClick);
                                room_Price.add(((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                totalCost[0] += (((length/24) * (width/24) * 12) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            case "Closet":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Closet.png"));
                                Image closet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(closet));
                                room_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Closet.png"));
                                closet = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(closet));
                                allFurnImages.add(new ImageIcon(closet));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Closet.png"));
                                closet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(closet));

                                room_Floor.add(floorClick);
                                room_Price.add(((length/24) * (width/24) * 7) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                totalCost[0] += (((length/24) * (width/24) * 7) + ((length/24 * paintCost) + (width/24 * paintCost) + (length/24 * width/24 * 3)));;
                                layers.add(room_Objects.get(k), new Integer(1));
                                room_Objects.get(k).setBounds(400, 400, width, length);
                                room_Objects.get(k).setVisible(true);
                                k++;
                                index = index + 4;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + room);
                        }
                        write.setVisible(false);
                        write2.setVisible(false);
                        click5.setVisible(false);
                        room_message.setVisible(false);
                        dropMenu.setVisible(false);
                        width_message.setVisible(false);
                        length_message.setVisible(false);
                        Cancel.setVisible(false);

                        //Reset text fields for next loop
                        write.setText(String.valueOf(0));
                        write2.setText(String.valueOf(0));
                        continue;
                    }
                }
            }
            //Window Input
            if (button_Clicked == 2) {
                if (click5.getModel().isPressed()) {
                    if (Integer.parseInt(DW_write.getText()) > 0) {
                        click5.setEnabled(false);
                        out.println("Confirm is Pressed");
                        width = Integer.parseInt(DW_write.getText()) * 2;

                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Window.png"));
                        Image windowf = myPicture.getScaledInstance(width, 12, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(windowf));
                        window_Objects.add(new JLabel((allFurnImages.get(index))));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Window2.png"));
                        windowf = myPicture.getScaledInstance(12, width, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(windowf));
                        allFurnImages.add(new ImageIcon(windowf));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Window.png"));
                        windowf = myPicture.getScaledInstance(width, 12, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(windowf));
                        window_Floor.add(floorClick);
                        window_Price.add(300);
                        totalCost[0] += 300;
                        layers.add(window_Objects.get(w),new Integer(2));
                        window_Objects.get(w).setBounds(400, 400, width, 12);
                        window_Objects.get(w).setVisible(true);
                        w++;
                        index = index + 4;

                        DW_write.setVisible(false);
                        DW_message.setVisible(false);
                        click5.setVisible(false);
                        Cancel.setVisible(false);
                        //Reset text fields for next loop
                        DW_write.setText(String.valueOf(0));
                        continue;
                    }
                }
            }
            //Door Input
            if (button_Clicked == 3) {
                if (click5.getModel().isPressed()) {
                    if (Integer.parseInt(DW_write.getText()) > 0) {
                        click5.setEnabled(false);
                        out.println("Confirm is Pressed");
                        width = Integer.parseInt(DW_write.getText()) * 2;

                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Door.png"));
                        Image door = myPicture.getScaledInstance(width, 12, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(door));
                        door_Objects.add(new JLabel(allFurnImages.get(index)));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Door2.png"));
                        door = myPicture.getScaledInstance(12, width, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(door));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Door3.png"));
                        door = myPicture.getScaledInstance(12, width, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(door));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Door4.png"));
                        door = myPicture.getScaledInstance(width, 12, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(door));

                        door_Floor.add(floorClick);
                        door_Price.add(450);
                        totalCost[0] += 450;
                        layers.add(door_Objects.get(d),new Integer(2));
                        out.println("Made it");
                        door_Objects.get(d).setBounds(400, 400, width, 12);
                        door_Objects.get(d).setVisible(true);
                        d++;
                        index = index + 4;

                        DW_write.setVisible(false);
                        DW_message.setVisible(false);
                        click5.setVisible(false);
                        Cancel.setVisible(false);
                        //Reset text fields for next loop
                        DW_write.setText(String.valueOf(0));
                        continue;
                    }
                }
            }
            //Floor Input
            if (button_Clicked == 4) {
                if (click5.getModel().isPressed() && stairsMade == 1) {
                    if (Integer.parseInt(write.getText()) > 0 && Integer.parseInt(write2.getText()) > 0) {
                        click5.setEnabled(false);

                        width = Integer.parseInt(write.getText()) * 12 * 2;
                        length = Integer.parseInt(write2.getText()) * 12 * 2;

                        if (floor_Objects.isEmpty() || ((lowerFloorH >= length) && (lowerFloorW >= width))){
                            bigger = true;
                            floorNum++;
                            stairsMade = 0;
                            out.println("Making Floor: " + width + " " + length);
                            myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Border.png"));
                            Image floorPiece = myPicture.getScaledInstance(width, 10, Image.SCALE_SMOOTH);
                            floor_Objects.add(new JLabel(new ImageIcon(floorPiece)));
                            floor_Floor.add(floorNum);
                            window.add(floor_Objects.get(f));
                            floor_Objects.get(f).setBounds(screenSizeW - 280 - width, 15, width, 5);
                            out.println(screenSizeW - 280 - width + " " + 15);
                            if (floor_Floor.get(f) != floorClick) {
                                floor_Objects.get(f).setVisible(false);
                            }

                            f++;
                            myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Border.png"));
                            floorPiece = myPicture.getScaledInstance(10, length, Image.SCALE_SMOOTH);
                            floor_Objects.add(new JLabel(new ImageIcon(floorPiece)));
                            floor_Floor.add(floorNum);
                            window.add(floor_Objects.get(f));
                            floor_Objects.get(f).setBounds(screenSizeW - 280 - width, 15, 5, length - 10);
                            out.println(screenSizeW - 280 - width + " " + 15);
                            if (floor_Floor.get(f) != floorClick) {
                                floor_Objects.get(f).setVisible(false);
                            }
                            f++;

                            myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Border.png"));
                            floorPiece = myPicture.getScaledInstance(width, 5, Image.SCALE_SMOOTH);
                            floor_Objects.add(new JLabel(new ImageIcon(floorPiece)));
                            floor_Floor.add(floorNum);
                            window.add(floor_Objects.get(f));
                            floor_Objects.get(f).setBounds(screenSizeW - 280 - width, length, width, 10);
                            out.println(screenSizeW - 280 - width + " " + length);
                            if (floor_Floor.get(f) != floorClick) {
                                floor_Objects.get(f).setVisible(false);
                            }
                            f++;
                            lowerFloorW = width;
                            lowerFloorH = length;
                        }

                        if(floorNum == 1) {
                            if(bigger) {
                                write.setVisible(false);
                                write2.setVisible(false);
                                click5.setVisible(false);
                                width_message.setVisible(false);
                                length_message.setVisible(false);
                                floor_dimension.setVisible(false);
                                Cancel.setVisible(false);
                                stairsMade = 1;
                                bigger = false;
                            }
                        }
                        else{
                            floor_dimension.setVisible(false);
                            stair_dimension.setVisible(true);
                        }

                        write.setText(String.valueOf(0));
                        write2.setText(String.valueOf(0));
                        click5.setEnabled(true);
                        continue;
                    }
                }

                //When more than one floor is made, it forces the user to create stairs
                if (click5.getModel().isPressed() && stairsMade == 0) {
                    if (Integer.parseInt(write.getText()) > 0 && Integer.parseInt(write2.getText()) > 0) {
                        bigger = false;
                        click5.setEnabled(false);
                        width = Integer.parseInt(write.getText()) * 12 * 2;
                        length = Integer.parseInt(write2.getText()) * 12 * 2;

                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Stairs.png"));
                        Image stairs = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(stairs));
                        stairs_Objects.add(new JLabel(allFurnImages.get(index)));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Stairs2.png"));
                        stairs = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(stairs));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Stairs3.png"));
                        stairs = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(stairs));
                        myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Stairs4.png"));
                        stairs = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                        allFurnImages.add(new ImageIcon(stairs));

                        stairs_Floor.add(floorClick);
                        stairs_Price.add(2000);
                        totalCost[0] += 2000;
                        layers.add(stairs_Objects.get(s),new Integer(2));
                        stairs_Objects.get(s).setBounds(400, 400, width, length);
                        stairs_Objects.get(s).setVisible(true);
                        out.println("Stairs being made: " + width + " " + length + "\n" + stairs_Objects.get(s));

                        write.setVisible(false);
                        write2.setVisible(false);
                        click5.setVisible(false);
                        width_message.setVisible(false);
                        length_message.setVisible(false);
                        stair_dimension.setVisible(false);
                        Cancel.setVisible(false);
                        s++;
                        index = index + 4;
                        stairsMade = 1;
                        //Reset text fields for next loop
                        write.setText(String.valueOf(0));
                        write2.setText(String.valueOf(0));
                        continue;
                    }
                }
            }
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
            //Furniture Input
            if (button_Clicked == 5) {
                if (click5.getModel().isPressed()) {
                    if (Integer.parseInt(write.getText()) > 0 && Integer.parseInt(write2.getText()) > 0) {
                        click5.setEnabled(false);
                        out.println("Confirm is Pressed");
                        width = Integer.parseInt(write.getText()) * 2;
                        length = Integer.parseInt(write2.getText()) * 2;
                        furniture = furnDropMenu.getSelectedItem().toString();
                        switch (furniture) {
                            case "Couch":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Couch.png"));
                                Image couch = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(couch));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Couch2.png"));
                                couch = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(couch));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Couch3.png"));
                                couch = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(couch));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Couch4.png"));
                                couch = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(couch));

                                furn_Floor.add(floorClick);
                                furn_Price.add(500);
                                totalCost[0] += 500;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Bed":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bed.png"));
                                Image bed = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bed));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bed2.png"));
                                bed = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bed));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bed3.png"));
                                bed = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bed));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Bed4.png"));
                                bed = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(bed));

                                furn_Floor.add(floorClick);
                                furn_Price.add(750);
                                totalCost[0] += 750;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Sink":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Sink.png"));
                                Image sink = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(sink));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Sink2.png"));
                                sink = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(sink));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Sink3.png"));
                                sink = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(sink));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Sink4.png"));
                                sink = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(sink));

                                furn_Floor.add(floorClick);
                                furn_Price.add(400);
                                totalCost[0] += 400;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Toilet":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Toilet.png"));
                                Image toilet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(toilet));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Toilet2.png"));
                                toilet = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(toilet));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Toilet3.png"));
                                toilet = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(toilet));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Toilet4.png"));
                                toilet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(toilet));

                                furn_Floor.add(floorClick);
                                furn_Price.add(250);
                                totalCost[0] += 250;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Table":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Table.png"));
                                Image table = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(table));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                table = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(table));
                                allFurnImages.add(new ImageIcon(table));
                                table = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(table));

                                furn_Floor.add(floorClick);

                                if((width < 24) && (length < 24)){
                                    furn_Price.add(60);
                                    totalCost[0] += 60;
                                }
                                else if((width < 96) && (length < 36 )){
                                    furn_Price.add(150);
                                    totalCost[0] += 150;
                                }
                                else{
                                    furn_Price.add(400);
                                    totalCost[0] += 400;
                                }

                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Chair":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Chair.png"));
                                Image chair = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(chair));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Chair2.png"));
                                chair = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(chair));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Chair3.png"));
                                chair = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(chair));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Chair4.png"));
                                chair = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(chair));

                                furn_Floor.add(floorClick);
                                furn_Price.add(75);
                                totalCost[0] += 75;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Shelves":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Shelf.png"));
                                Image shelf = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(shelf));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Shelf2.png"));
                                shelf = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(shelf));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Shelf3.png"));
                                shelf = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(shelf));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Shelf4.png"));
                                shelf = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(shelf));

                                furn_Floor.add(floorClick);
                                furn_Price.add(800);
                                totalCost[0] += 800;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Cabinets":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Cabinet.png"));
                                Image cabinet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(cabinet));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Cabinet2.png"));
                                cabinet = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(cabinet));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Cabinet3.png"));
                                cabinet = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(cabinet));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Cabinet4.png"));
                                cabinet = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(cabinet));

                                furn_Floor.add(floorClick);
                                furn_Price.add(800);
                                totalCost[0] += 800;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Oven":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Oven.png"));
                                Image oven = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(oven));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                oven = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(oven));
                                allFurnImages.add(new ImageIcon(oven));
                                oven = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(oven));

                                furn_Floor.add(floorClick);
                                furn_Price.add(1000);
                                totalCost[0] += 1000;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Washer":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Washer.png"));
                                Image washer = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(washer));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                washer = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(washer));
                                allFurnImages.add(new ImageIcon(washer));
                                washer = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(washer));

                                furn_Floor.add(floorClick);
                                furn_Price.add(600);
                                totalCost[0] += 600;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Dryer":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Dryer.png"));
                                Image dryer = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(dryer));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Dryer2.png"));
                                dryer = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(dryer));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Dryer3.png"));
                                dryer = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(dryer));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Dryer4.png"));
                                dryer = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(dryer));

                                furn_Floor.add(floorClick);
                                furn_Price.add(600);
                                totalCost[0] += 600;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            case "Fridge":
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Fridge.png"));
                                Image fridge = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(fridge));
                                furn_Objects.add(new JLabel(allFurnImages.get(index)));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Fridge2.png"));
                                fridge = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(fridge));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Fridge3.png"));
                                fridge = myPicture.getScaledInstance(length, width, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(fridge));
                                myPicture = ImageIO.read(new File("C:\\Users\\sspur\\OneDrive\\Desktop\\Capstone_Assets\\Fridge4.png"));
                                fridge = myPicture.getScaledInstance(width, length, Image.SCALE_SMOOTH);
                                allFurnImages.add(new ImageIcon(fridge));

                                furn_Floor.add(floorClick);
                                furn_Price.add(750);
                                totalCost[0] += 750;
                                layers.add(furn_Objects.get(i), new Integer(2));
                                furn_Objects.get(i).setBounds(400, 400, width, length);
                                furn_Objects.get(i).setVisible(true);
                                i++;
                                index = index + 4;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + furniture);
                        }

                        //new furniture(furniture, width, length, 15, passingFurn);//15 is test number, write function to do switch case statements
                        write.setVisible(false);
                        write2.setVisible(false);
                        click5.setVisible(false);
                        width_message.setVisible(false);
                        length_message.setVisible(false);
                        furnDropMenu.setVisible(false);
                        furn_message.setVisible(false);
                        Cancel.setVisible(false);
                        //Reset text fields for next loop
                        write.setText(String.valueOf(0));
                        write2.setText(String.valueOf(0));
                        continue;
                    }
                }
            }
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            //Allows Confirm to be hit again
            click5.setEnabled(true);
            //Displaying total cost of the house
            if (totalCost[0] > 0) {
                formattedCost[0] = String.format("%,d", totalCost[0]);
                xtotalCostOffset = 10;
                houseCost.setText("Estimated House Cost: $" + formattedCost[0]);

                if (totalCost[0] == 1000) {
                    houseCost.setText("Estimated House Cost: $1,000");
                }

                if (totalCost[0] > 1000) {
                    houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                    xtotalCostOffset = 15;

                    if (totalCost[0] > 10000) {
                        houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                        xtotalCostOffset = 20;
                    }
                    if (totalCost[0] > 100000) {
                        houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                        xtotalCostOffset = 25;
                    }
                }

                if (totalCost[0] > 1000000) {
                    houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                    xtotalCostOffset = 30;

                    if (totalCost[0] > 10000000) {
                        houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                        xtotalCostOffset = 35;
                    }
                    if (totalCost[0] > 100000000) {
                        houseCost.setText("Estimated House Cost: $" + formattedCost[0]);
                        xtotalCostOffset = 40;
                    }
                }

            }
            stair_Num.setText("Floor #" + floorClick);
            //Quits code when window is closed
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }
}