import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.util.WaveRecorder;
import com.jsyn.unitgen.ExponentialRamp;

import java.io.File;
import java.io.IOException;

public class JSynRecordingExample {

    public static void main(String[] args) throws InterruptedException, IOException {

        // Create a context for the synthesizer.
        Synthesizer synth = JSyn.createSynthesizer();
        // Start synthesizer using default stereo output at 44100 Hz.
        synth.start();
        // Add a tone generator.
        SineOscillator osc = new SineOscillator();
        synth.add(osc);
        // Add a stereo audio output unit.
        LineOut lineOut = new LineOut();
        synth.add(lineOut);
        // Connect the oscillator to both channels of the output.
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);



        // Set the frequency and amplitude for the sine wave.
        osc.frequency.set(345.0);
        osc.amplitude.set(0.6);
        // We only need to start the LineOut. It will pull data from the oscillator.
        lineOut.start();

        // Create a WaveRecorder to capture the audio output
        File waveFile = new File("temp_recording.wav");
        WaveRecorder recorder = new WaveRecorder(synth, waveFile);
        recorder.start();
        // Connect the LineOut to the recorder
        osc.output.connect(0, recorder.getInput(), 0);
        osc.output.connect(0, recorder.getInput(), 1);

        // Record for 5 seconds
        synth.sleepFor(10.0);
        recorder.stop();
        recorder.close();
        synth.stop();
        System.out.println("Wrote audio to: " + waveFile);
    }
}