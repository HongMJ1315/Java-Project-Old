import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import java.lang.reflect.GenericSignatureFormatError;

public class GetDevice {
    public void getDevice() {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println(mixerInfos.length);
        int i = 0;
        for (Mixer.Info info : mixerInfos) {
            Mixer mixer = AudioSystem.getMixer(info);
            if (mixer.getMaxLines(Port.Info.MICROPHONE) > 0) {
                System.out.println("Device: " + info.getName() + " " + i);
            }
            i++;
        }
    }
    public static void main(String arg[]){
        GetDevice getDevice = new GetDevice();
        getDevice.getDevice();
    }
}
