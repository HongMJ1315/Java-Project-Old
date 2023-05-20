import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.unitgen.*;
import com.jsyn.util.WaveRecorder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

public class Recorder {
    Synthesizer synth;
    LineOut lineOut;
    WaveRecorder recorder;
    File waveFile;
    LineIn lineIn;
//    UnitGenerator unitGenerator;
    public Recorder(Scanner scanner) {
        try {
            synth = JSyn.createSynthesizer();
            lineOut = new LineOut();
            lineIn = new LineIn();
            System.out.println("請輸入檔案名稱");
            String fileName = scanner.nextLine();
            waveFile = new File(fileName);
            recorder = new WaveRecorder(synth, waveFile);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Start(){
        synth.add(lineIn);
        synth.add(lineOut);
        lineIn.output.connect(0, lineOut.input, 0);
        lineIn.output.connect(1, lineOut.input, 1);
        lineIn.output.connect(0, recorder.getInput(), 0);
        lineIn.output.connect(1, recorder.getInput(), 1);

        synth.start(44100, AudioDeviceManager.USE_DEFAULT_DEVICE, 2,
                AudioDeviceManager.USE_DEFAULT_DEVICE, 2);
        lineOut.start();
        boolean isRecording = true;
        while(isRecording) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if (str.equals("start")){
                    recorder.start();
                }
                else if (str.equals("exit")){
                    recorder.stop();
                    recorder.close();
                    synth.stop();
                    br.close();
                    System.gc();
                    break;
                }
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Reached the end of recorder.Start()");

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Recorder recorder = new Recorder(scanner);
        recorder.Start();
        System.out.println("Recorder");
        scanner.close();
        System.out.println("Recorder end");
        System.gc();
    }
}
