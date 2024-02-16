package junit;

import com.example.ispw.dao.EpisodeDAO;
import com.example.ispw.model.Track;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestMusicManager {

    @Test
    void addNewSong() {
        int size1;
        int size2;

        List<Track> trackList;
        trackList = EpisodeDAO.getEpTracks(1, 1, "Dynasty");
        size1 = trackList.size();
        EpisodeDAO.addTrack(1, 1, "Coldplay", "The Scientist", "Dynasty");
        trackList = EpisodeDAO.getEpTracks(1, 1, "Dynasty");
        size2 = trackList.size();

        assertEquals(size1+1, size2);
    }

}