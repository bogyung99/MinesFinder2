package pt.technic.apps.minesfinder;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
public class Sound {
       public void mainmusicPlay() {
              File bgm;
              AudioInputStream stream;
              AudioFormat format;
              DataLine.Info info;
              
              bgm = new File(System.getProperty("user.dir") + "/src/pt/technic/apps/minesfinder/resources/" + "록맨.wav"); // 사용시에는 개별 폴더로 변경할 것
            
              Clip clip;
              
              try {
                     stream = AudioSystem.getAudioInputStream(bgm);
                     format = stream.getFormat();
                     info = new DataLine.Info(Clip.class, format);
                     clip = (Clip)AudioSystem.getLine(info);
                     clip.open(stream);
                     clip.start();
                     
              } catch (Exception e) {
                     System.out.println("err : " + e);
                     }
              
       }
       
       public void bombPlay() {
           File bombbgm;
           AudioInputStream stream;
           AudioFormat format;
           DataLine.Info info;
           
           bombbgm = new File(System.getProperty("user.dir") + "/src/pt/technic/apps/minesfinder/resources/" + "Bomb.wav");
           Clip bombclip;
           
           try {
               stream = AudioSystem.getAudioInputStream(bombbgm);
               format = stream.getFormat();
               info = new DataLine.Info(Clip.class, format);
               bombclip = (Clip)AudioSystem.getLine(info);
               bombclip.open(stream);
               bombclip.start();
               
        } catch (Exception e) {
               System.out.println("err : " + e);
               }
           
    }

}

