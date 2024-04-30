package de.dhbw.swe.main.gui;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.inject.AssistedFactory;

import javax.swing.*;
import java.util.HashMap;

@AssistedFactory(SearchField.Factory.class)
public interface SearchField {

    HashMap<String, InputSearchComponent> getSearchComponents();

    void addInputSearchComponent(String componentName, String componentUnit, int minInterval, int maxInterval);

    void removeInputSearchComponent(String componentName);

    void clearInputSearchComponents();

    InputSearchComponent getInputSearchComponent(String name);

    JComponent getSearchField();

    interface Factory {
        SearchField create(@Assisted("grpcClient") GrpcClient grpcClient);
    }
}
