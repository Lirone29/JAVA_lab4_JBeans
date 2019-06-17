import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Jun 14 22:49:17 CEST 2019
 */



/**
 * @author Eliza Kalata
 */
public class NoteDesigner extends JPanel {
    public NoteDesigner() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Eliza Kalata
        note1 = new Note();
        noteCustomizer1 = new NoteCustomizer();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FlowLayout());

        //---- note1 ----
        note1.setMinX(450);
        note1.setMinimumSize(new Dimension(500, 600));
        note1.setTitle("gg");
        add(note1);
        add(noteCustomizer1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Eliza Kalata
    private Note note1;
    private NoteCustomizer noteCustomizer1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
