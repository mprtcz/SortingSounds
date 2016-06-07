package com.mprtcz.sortingSounds.Sounds;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

/**
 * Created by Azet on 2016-05-02.
 */
public class MyBeeper {
    private Clip clip;

    public void setUpSound(int framesPerWavelength) {
        try {
            generateTone(framesPerWavelength);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTone(int framesPerWavelengthValue)
            throws LineUnavailableException {
        if (clip != null) {
            clip.stop();
            clip.close();
        } else {
            clip = AudioSystem.getClip();
        }
        boolean addHarmonic = true; //add harmonic

        int sampleRateInt = 8000;

        float sampleRate = (float) sampleRateInt;

        int wavelengths = 2;
        byte[] buf = new byte[2 * framesPerWavelengthValue * wavelengths];
        AudioFormat af = new AudioFormat(
                sampleRate,
                8,  // sample size in bits
                2,  // channels
                true,  // signed
                false  // bigendian
        );

        for (int i = 0; i < framesPerWavelengthValue * wavelengths; i++) {
            double angle = ((float) (i * 2) / ((float) framesPerWavelengthValue)) * (Math.PI);
            buf[i * 2] = getByteValue(angle);
            if (addHarmonic) {
                buf[(i * 2) + 1] = getByteValue(2 * angle);
            } else {
                buf[(i * 2) + 1] = buf[i * 2];
            }
        }

        try {
            AudioInputStream ais = new AudioInputStream(
                    new ByteArrayInputStream(buf),
                    af,
                    buf.length / 2);

            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte getByteValue(double angle) {
        int maxVol = 50;
        return (new Integer(
                (int) Math.round(
                        Math.sin(angle) * maxVol))).
                byteValue();
    }

    public void loopSound(boolean commence) {
        if (commence) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.stop();
        }
    }
}
