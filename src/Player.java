import com.jsyn.JSyn;
import com.jsyn.data.FloatSample;
import com.jsyn.Synthesizer;
import com.jsyn.util.SampleLoader;
import com.jsyn.unitgen.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//JSYN 錄音
public class Player {
    File file;
    Synthesizer synth;
    LineOut lineOut;
    VariableRateMonoReader reader;
    FloatSample sample;
    public Player(Scanner scanner) {
        try {
            System.out.println("請輸入檔案名稱");
            String fileName = scanner.nextLine();
            file = new File(fileName);
            System.out.println(file);
            synth = JSyn.createSynthesizer();
            lineOut = new LineOut();
            reader = new VariableRateMonoReader();
            SampleLoader.setJavaSoundPreferred(false);
            sample = SampleLoader.loadFloatSample(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Start(){

        synth.add(reader);
        synth.add(lineOut);
        reader.output.connect(0, lineOut.input, 0);
        reader.output.connect(0, lineOut.input, 1);

        synth.start();
        reader.rate.set(sample.getFrameRate());

        lineOut.start();
        reader.dataQueue.queue(sample);
        while(true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if(str.equals("stop")) {
                    lineOut.stop();
                }
                else if (str.equals("start")){
                    lineOut.start();
                }
                else if (str.equals("exit")){
                    synth.stop();
                    break;
                }
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}