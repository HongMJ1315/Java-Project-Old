import com.jsyn.*;
import com.jsyn.unitgen.*;
import com.jsyn.data.*;
import com.softsynth.shared.time.TimeStamp;
public class toneGenerater {
    public static void main(String[] args) {
        Synthesizer synth = JSyn.createSynthesizer();

        SineOscillator sineOsc = new SineOscillator();
        sineOsc.frequency.set(220.0);

        LineOut lineOut = new LineOut();

        synth.add(sineOsc);
        synth.add(lineOut);

        sineOsc.output.connect(0, lineOut.input, 0);
        sineOsc.output.connect(0, lineOut.input, 1);

        synth.start();
        lineOut.start();
        try {
            synth.sleepFor(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lineOut.stop();
        synth.stop();
    }
}
