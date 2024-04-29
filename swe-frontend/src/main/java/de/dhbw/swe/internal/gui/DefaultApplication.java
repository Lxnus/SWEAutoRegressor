package de.dhbw.swe.internal.gui;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.gui.Application;
import de.dhbw.swe.main.gui.SearchField;
import io.grpc.netty.GrpcSslContexts;
import io.netty.handler.ssl.SslContext;

import javax.net.ssl.SSLException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class DefaultApplication extends JFrame implements Application {

    private final String windowTitle;

    private final int windowWidth;
    private final int windowHeight;

    private final GrpcClient grpcClient;
    private final SearchField searchField;

    @Inject
    public DefaultApplication(@Assisted("windowTitle") String windowTitle,
                              @Assisted("windowWidth") int windowWidth,
                              @Assisted("windowHeight") int windowHeight,
                              SearchField.Factory searchFieldFactory) {
        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.grpcClient = GrpcClient.Factory.create();
        SslContext sslContext;
        try {
            sslContext = this.loadSSLCredentials();
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
        this.grpcClient.start(sslContext);

        this.searchField = searchFieldFactory.create(this.grpcClient);
    }

    @Override
    public void start() {
        this.setTitle(this.windowTitle);
        this.setSize(this.windowWidth, this.windowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(this.searchField.getSearchField(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    private SslContext loadSSLCredentials() throws SSLException {
        ClassLoader classLoader = getClass().getClassLoader();
        File serverCACertFile = new File(Objects.requireNonNull(classLoader.getResource("ca-cert.pem")).getFile());
        return GrpcSslContexts.forClient()
                .trustManager(serverCACertFile)
                .build();
    }

    @Override
    public void stop() {
        this.grpcClient.stop();
        this.dispose();
    }

    @Override
    public String getApplicationName() {
        return this.windowTitle;
    }

    @Override
    public int getApplicationWidth() {
        return this.windowWidth;
    }

    @Override
    public int getApplicationHeight() {
        return this.windowHeight;
    }

    @Override
    public JFrame getApplication() {
        return this;
    }

    @Override
    public SearchField getSearchField() {
        return searchField;
    }
}
