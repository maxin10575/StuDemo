package cn.mx.play;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.mx.bo.NoteBO;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Audio extends PlaybackListener {
    private static InputStream is;
    private Player player;

    private static ExecutorService service = Executors.newCachedThreadPool();
    private static final Log log = LogFactory.get();

    public Audio(String name) {
        this(name, null);
    }


    public Audio(String name, NoteBO.Mode mode) {
        String path = ResourceUtil.getResource("pianoKey").getPath() + File.separator;
        path = path + name + ".mp3";
        is = ResourceUtil.getStream(path);
        try {


            if (NoteBO.Mode.ACCOMPANIMENTS.equals(mode)) {
                player = new Player(is);
//                player = new Player(is, 0.5f);
            } else {
                player = new Player(is);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }


    public void start() {
        service.submit(() -> {
            try {

                player.play();
            } catch (Exception e) {
                log.error(e);

            }

        });

    }
}
