package hotciv.standard;
import hotciv.framework.*;
import java.util.ArrayList;

public class TranscriptObserver implements Observer {
    
    private ArrayList<String> transcript = new ArrayList<String>();

    public void update(String transcription){
        transcript.add(transcription);
    }

    public ArrayList<String> getTranscript(){
        return transcript;
    }
}
