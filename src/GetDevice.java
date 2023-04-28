import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

public class GetDevice {
    public void getDevice() {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println(mixerInfos.length);
        for (Mixer.Info info : mixerInfos) {
            Mixer mixer = AudioSystem.getMixer(info);
            if (mixer.getMaxLines(Port.Info.SPEAKER) > 0) {
                System.out.println("Device: " + info.getName() );
            }
        }
    }
}
