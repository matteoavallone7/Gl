package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.RequestBean;
import com.example.ispw.controller.RequestController;
import com.example.ispw.dao.RequestDAO;
import com.example.ispw.model.Request;
import com.example.ispw.session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GUIManageMusicController {

    @FXML
    private TextField episodeNumber;

    @FXML
    private TextField seasonNumber;

    @FXML
    private TableView<Request> requestTable;
    @FXML
    private TableColumn<Request, Integer> frequency;
    @FXML
    private TableColumn<Request, String> description;


    private Parent pageContainer;

    public void setCurrentPage(Parent previousPage) {
        this.pageContainer = previousPage;
    }
    public void initialize(Parent pageContainer) {
        setCurrentPage(pageContainer);
        RequestController requestController = new RequestController();
        Session currentSession = Session.getCurrentSession();
        List<RequestBean> requestBeanList = requestController.getRequests(currentSession.getSeriesOffAccountBean().getSeriesName());
        setTableData(requestBeanList);
    }



    public void goBack() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TvSeriesAccHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void searchSong() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/SearchMusic.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUISearchMusicController guiSearchMusicController = fxmlLoader.getController();
        guiSearchMusicController.initialize(Integer.parseInt(episodeNumber.getText()), Integer.parseInt(seasonNumber.getText()), pageContainer);
        stage.setScene(scene);
        stage.show();
    }

    public void setTableData(List<RequestBean> requestBeanList) {
        frequency.setCellValueFactory(new PropertyValueFactory<>("Frequency"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));

        requestTable.getItems().clear();

        Iterator<RequestBean> iterator = requestBeanList.iterator();
        ObservableList<Request> observableList = FXCollections.observableArrayList();

        while (iterator.hasNext()) {
            RequestBean requestBean = iterator.next();
            Request request = new Request(requestBean.getFrequency(), requestBean.getRequest());
            observableList.add(request);
        }

        requestTable.setItems(observableList);
    }

    public void markRequest() {
        int selectedIdx = requestTable.getSelectionModel().getSelectedIndex();
        String requestText = null;
        if (selectedIdx >= 0) {

            requestText = (String) requestTable.getColumns().get(1).getCellObservableValue(selectedIdx).getValue();
            requestTable.getItems().remove(selectedIdx);
        }

        RequestController requestController = new RequestController();
        requestController.changeRequestStatus(requestText);

    }


}
