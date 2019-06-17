import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note extends JPanel implements Serializable, PropertyChangeListener {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private String title = "My Notes";
    private Date startDate;
    private Date endDate;
    //private int endDate = 5;
    private Font myFont = new Font("Segoe Print", Font.PLAIN, 14);
    private Color myBackground = Color.decode("0x00FF00");

    private int minX = 400;
    private int minY = 600;

    private ArrayList<NoteData> notes = new ArrayList<NoteData>() {
        {
            add(new NoteData("Note 1", "22-10-2019"));
            add(new NoteData("Note 2", "24-11-2018"));
            add(new NoteData("Note 3", "10-10-2020"));
        }
    };

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoes = new VetoableChangeSupport(this);



    private JLabel titleLabel;
    private JLabel timeLabel;
    private JTextField timeTextField;
    private JLabel endTimeLabel;
    private JTextField endTimeTextArea;

    private JButton filtrButton;
    private JButton deleteButton;
    private JList<String> listOfNotes;

    private JLabel addTitleLabel;
    private JTextField addTitleTextField;
    private JLabel addDateLabel;
    private JTextField addDateTextFiels;
    private JButton addButton;

    private DefaultListModel<String> listModel;


    public Note() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel(title);
        timeLabel = new JLabel("Begin date [dd-MM-yyyy]:");
        timeTextField = new JTextField("");
        timeTextField.setSize(300,20);
        endTimeLabel = new JLabel("End Date [dd-MM-yyyy]");
        endTimeTextArea = new JTextField("");
        endTimeTextArea.setSize(300,20);
        filtrButton = new JButton("Show Notes");
        deleteButton = new JButton("Delete Note");
        addTitleLabel = new JLabel("Title");
        addTitleTextField = new JTextField("");
        addDateLabel = new JLabel("Date [dd-MM-yyyy]");
        addDateTextFiels = new JTextField("");
        addButton = new JButton("Add Note");
        listOfNotes = new JList<>();

        titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (NoteData data : notes) {
                    if (listOfNotes.getSelectedValue().equals(data.toString())) {
                        notes.remove(data);
                    }
                }

                listModel.clear();

                for (NoteData data : notes) {
                    if (data.getDate().after( getStartDate()) && data.getDate().before(getEndDate())) {
                        listModel.addElement(data.toString());
                    }
                }

                listOfNotes.setModel(listModel);

            }
        });

        filtrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setStartDate(timeTextField.getText());
                setEndDate(endTimeTextArea.getText());

                listModel.clear();

                for (NoteData data : notes) {
                    if (data.getDate().after( getStartDate()) && data.getDate().before(getEndDate())) {
                        listModel.addElement(data.toString());
                    }
                }

                listOfNotes.setModel(listModel);

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notes.add(new NoteData(addTitleTextField.getText(), addDateTextFiels.getText()));

                listModel.clear();

                for (NoteData data : notes) {
                    listModel.addElement(data.toString());
                }

                listOfNotes.setModel(listModel);
            }
        });


        titleLabel.setFont(new Font("Bodoni", Font.PLAIN, 18));

        this.setMinimumSize(new Dimension(minX, minY));

        listModel = new DefaultListModel<String>();

        for (NoteData data : notes) {
            listModel.addElement(data.toString());
        }

        listOfNotes.setModel(listModel);


        add(titleLabel);
        add(timeLabel);
        add(timeTextField);
        add(endTimeLabel);
        add(endTimeTextArea);
        add(filtrButton);
        add(new JScrollPane(listOfNotes));
        add(deleteButton);
        add(addTitleLabel);
        add(addTitleTextField);
        add(addDateLabel);
        add(addDateTextFiels);
        add(addButton);
    }


    public String getTitle() {
        return title;
    }

    public void addPropertyChangeListener(PropertyChangeListener property){
        changes.addPropertyChangeListener(property);
    }
    public void removePropertyChangeListener(PropertyChangeListener property){
        changes.removePropertyChangeListener(property);
    }
    public void addVetoableChangeListener(VetoableChangeListener veto){
        vetoes.addVetoableChangeListener(veto);
    }
    public void removeVetoableChangeListener(VetoableChangeListener veto){
        vetoes.removeVetoableChangeListener(veto);
    }


    /*
    public String setTitle(String noteTitle) {
        this.noteTitle = noteTitle;
        titleLabel.setText(noteTitle);
    }

*/
    public ArrayList<NoteData> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NoteData> notes) {
        this.notes = notes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = formatter.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = formatter.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String str) {
        String oldTitle = getTitle();
        this.title = str;
        this.titleLabel.setText(str);
        changes.firePropertyChange("title", oldTitle, str);
    }

    public String getMyBackground() {
        String r, g, b;
        if (myBackground.getRed()<16) r = Integer.toHexString(myBackground.getRed()) + "0"; else r = Integer.toHexString(myBackground.getRed());
        if (myBackground.getGreen()<16) g = Integer.toHexString(myBackground.getGreen()) + "0"; else g = Integer.toHexString(myBackground.getGreen());
        if (myBackground.getBlue()<16) b = Integer.toHexString(myBackground.getBlue()) + "0"; else b = Integer.toHexString(myBackground.getBlue());
        return ("0x" + r + g + b);
    }

    public void setMyFonts(String strFont){
        Font font = new Font(strFont, Font.PLAIN, 14);
        this.myFont = font;
    }

    public String getMyFonts() {
        return myFont.getFontName();
    }


    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
        this.setMinimumSize(new Dimension(minX, minY));
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
        this.setMinimumSize(new Dimension(minX, minY));
    }

    public static void main(String[] args) {
        new Note();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
