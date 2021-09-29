package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.InputStream;

@PageTitle("Upload")
@Route(value = "upload", layout = MainLayout.class)
public class UploadView extends Main {

    public UploadView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Upload"));
        createUploads();
    }

    private void createUploads() {
        Div uploads = new Div();
        uploads.addClassNames("flex", "flex-col", "gap-m");

        Upload upload = createUpload(true);
        uploads.add(upload);

        upload = createUpload(true);
        upload.setWidth("200px");
        uploads.add(upload);

        upload = createUpload(false);
        uploads.add(upload);

        add(uploads);
    }

    private Upload createUpload(boolean dnd) {
        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(multiFileMemoryBuffer);

        upload.addSucceededListener(event -> {
            // Determine which file was uploaded
            String fileName = event.getFileName();

            // Get input stream specifically for the finished file
            InputStream fileData = multiFileMemoryBuffer.getInputStream(fileName);
            long contentLength = event.getContentLength();
            String mimeType = event.getMIMEType();

            // Do something with the file data
            System.out.println("Great success!");
        });

        Button button;
        if (dnd) {
            button = new Button("Drag and drop files here or click to upload");
            button.addThemeName("upload");
        } else {
            button = new Button("Add files");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        }
        upload.setUploadButton(button);
        return upload;
    }
}
