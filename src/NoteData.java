import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteData {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String note;
    private int dateInt;
    private Date date;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = formatter.parse(date);
    }

    NoteData(String note, String date){
        this.note = note;
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return  " Date: " + formatter.format(date)+ "  |  Title: "+ note;
    }
}
