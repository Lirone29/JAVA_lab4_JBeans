import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.io.Serializable;

public class NoteCustomizer extends JPanel implements Customizer, Serializable, ActionListener  {

    private static final long serialVersionUID = 1L;
    private static final int XPREFSIZE = 200;
    private static final int YPREFSIZE = 120;
    private Note bean;
    private JTextField data;
   // private JTextField titleField;
    JButton dataButton;

    private JLabel title = new JLabel("Nazwa:", SwingConstants.CENTER);
    private JLabel color = new JLabel("Kolor:", SwingConstants.CENTER);
    private JLabel font = new JLabel("Czcionka:", SwingConstants.CENTER);
    private JTextField fontField = new JTextField();
    private JTextField titleField = new JTextField();
    private JButton colorChooser = new JButton("Wybierz kolor");

    public NoteCustomizer() {

        setPreferredSize(new Dimension(300, 100));
        JPanel panel = new JPanel(new GridLayout(2,3,15,15));

        titleField.setHorizontalAlignment(SwingConstants.CENTER);
        fontField.setHorizontalAlignment(SwingConstants.CENTER);

        colorChooser.addActionListener(this);

        titleField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }

            public void insertUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }

            public void removeUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }
        });

        fontField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
                setMyFonts(fontField.getText());
            }

            public void insertUpdate(DocumentEvent evt) {
                setMyFonts(fontField.getText());
            }

            public void removeUpdate(DocumentEvent evt) {
                setMyFonts(fontField.getText());
            }
        });

        this.add(panel);

        panel.add(title);
        panel.add(color);
        panel.add(font);

        panel.add(titleField);
        panel.add(colorChooser);
        panel.add(fontField);

        panel.setVisible(true);

    }

    public void setMyFonts(String newValue) {
        if (bean == null)
            return;
        String oldValue = bean.getMyFonts();
        bean.setMyFonts(newValue);
    }

    public void setTitle(String newValue) {
        if (bean == null)
            return;
        String oldValue = bean.getTitle();
        bean.setTitle(newValue);
        firePropertyChange("title", oldValue, newValue);
    }

    public void setBeginDate(String newValue) {
        if (bean == null)
            return;
        String oldValue = bean.getStartDate().toString();
        bean.setStartDate(newValue);
        firePropertyChange("startDate", oldValue, newValue);
    }
    @Override
    public void setObject(Object obj) {
        bean = (Note) obj;

        titleField.setText(this.bean.getTitle());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

       // if (source.equals(dataButton)){
            //this.setTitle(data.getText());
          //  NoteCustomizer.this.setTitle(NoteCustomizer.this.titleField.getText());
        //}

    }
}
