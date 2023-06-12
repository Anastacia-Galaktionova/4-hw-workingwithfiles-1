package com.sample.drivers.screen.driver;

import io.jmix.ui.component.FileUploadField;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.Image;
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

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        photoImage.setVisible(false);
    }

    @Subscribe("photoFileUpload")
    public void onPhotoFileUploadValueChange(HasValue.ValueChangeEvent<byte[]> event) {
        photoFileUpload.getId();
        photoImage.setVisible(true);
    }


}