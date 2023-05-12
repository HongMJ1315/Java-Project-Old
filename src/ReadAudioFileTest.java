import com.jsyn.JSyn;
import com.jsyn.data.FloatSample;
import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.util.SampleLoader;
import com.jsyn.unitgen.*;
import java.io.File;

//JSYN 錄音
public class ReadAudioFileTest {
    public static void main(String[] args) {
        // 创建 JSyn 实例
        try {
            File file = new File("galaxy.wav");
            System.out.println(file);
            Synthesizer synth = JSyn.createSynthesizer();
            LineOut lineOut = new LineOut();
            VariableRateMonoReader reader = new VariableRateMonoReader();
            SampleLoader.setJavaSoundPreferred(false);
            FloatSample sample = SampleLoader.loadFloatSample(file);

            synth.add(reader);
            synth.add(lineOut);
            reader.output.connect(0, lineOut.input, 0);
            reader.output.connect(0, lineOut.input, 1);

            synth.start();
            reader.rate.set(sample.getFrameRate());

            lineOut.start();
            reader.dataQueue.queue(sample);
        }catch (Exception e){
            e.printStackTrace();
        }




    }
}