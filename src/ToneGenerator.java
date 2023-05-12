import com.jsyn.*;
import com.jsyn.unitgen.*;
import com.jsyn.data.*;
import com.softsynth.shared.time.TimeStamp;
public class ToneGenerator {
    public SineOscillator tone(double frequency) {

        SineOscillator sineOsc = new SineOscillator();
        sineOsc.frequency.set(frequency);
        return  sineOsc;
    }
}
