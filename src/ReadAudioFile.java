import com.jsyn.JSyn;
import com.jsyn.data.FloatSample;
import com.jsyn.Synthesizer;
import com.jsyn.util.SampleLoader;
import com.jsyn.unitgen.*;
import java.io.File;

public class ReadAudioFile {
    public static void main(String[] args) {
        // 创建 JSyn 实例
        GetDevice getDevice = new GetDevice();
        getDevice.getDevice();
        Synthesizer synth = JSyn.createSynthesizer();

        try {
            // 读取音频文件
            File file = new File("inception.wav");
            System.out.println(file);
            FloatSample sample = SampleLoader.loadFloatSample(file);

            VariableRateMonoReader reader = new VariableRateMonoReader();
            synth.add(reader);

            Multiply amp = new Multiply();
            synth.add(amp);
            amp.inputB.set(1.5); // 将音量增加到 1.5 倍

            LineOut lineOut = new LineOut();
            synth.add(lineOut);
            reader.output.connect(amp.inputA);
            amp.output.connect(lineOut.input);
            reader.dataQueue.queue(sample);
            reader.rate.set(sample.getFrameRate());
            System.out.println(reader.dataQueue.getName());
            synth.start();
            reader.start();
            System.out.println("playing");
            synth.sleepFor(10);
            synth.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}