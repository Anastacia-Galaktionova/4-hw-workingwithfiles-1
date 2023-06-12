package com.sample.drivers.screen.driver;

import com.sample.drivers.entity.Document;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;


@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
    @Autowired
    private Image photoImage;
    @Autowired
    private FileUploadField photoFileUpload;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        photoImage.setVisible(false);
    }

    @Subscribe("photoFileUpload")
    public void onPhotoFileUploadValueChange(HasValue.ValueChangeEvent<byte[]> event) {
        photoFileUpload.getId();
        photoImage.setVisible(true);
    }

    @Install(to = "documentsTable.documentFileColumn", subject = "columnGenerator")
    private Component documentsTableDocumentFileColumnColumnGenerator(Document document) {

        if (document.getDocumentFile() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);

            linkButton.setCaption(document.getDocumentFile().getFileName());
            linkButton.addClickListener(clickEvent ->
                    downloader.download(document.getDocumentFile()));

            return linkButton;
        }


        return null;
    }


}