package junit;

import com.example.ispw.bean.SearchBean;
import com.example.ispw.controller.BrowseSeriesController;
import com.example.ispw.exceptions.SeriesNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTvSeriesTest {

    @Test
    void testSearchSeries() {
        int searchResult;
        SearchBean searchBean = new SearchBean();
        searchBean.setName("Taxi Driver");
        BrowseSeriesController browseSeriesController = new BrowseSeriesController();
        try {
            browseSeriesController.searchSeries(searchBean);
            searchResult = 1;
        } catch (SeriesNotFoundException e) {
            searchResult = 2;
        }

        assertEquals(2, searchResult);
    }

}