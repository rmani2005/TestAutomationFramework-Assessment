
package com.clover.solution.ui.webservices;

import org.openqa.selenium.Alert;

import java.util.function.Consumer;
import java.util.function.Function;

public class DialogService extends WebService {
    public void handle(Function<Object, Alert> function, DialogButton dialogButton) {
    	Alert alert = getMasterDriver().switchTo().alert();
        function.apply(alert);
        if (dialogButton == DialogButton.OK) {
            alert.accept();
            getMasterDriver().switchTo().defaultContent();
        } else {
            alert.dismiss();
            getMasterDriver().switchTo().defaultContent();
        }
    }

    public void handle(Consumer<Alert> function, DialogButton dialogButton) {
        Alert alert = getMasterDriver().switchTo().alert();
        function.accept(alert);
        if (dialogButton == DialogButton.OK) {
            alert.accept();
            getMasterDriver().switchTo().defaultContent();
        } else {
            alert.dismiss();
            getMasterDriver().switchTo().defaultContent();
        }
    }

    public void handle(DialogButton dialogButton) {
    	Alert alert = getMasterDriver().switchTo().alert();
        if (dialogButton == DialogButton.OK) {
            alert.accept();
            getMasterDriver().switchTo().defaultContent();
        } else {
            alert.dismiss();
            getMasterDriver().switchTo().defaultContent();
        }
    }

    public void handle() {
        handle(new Consumer<Alert>() {
			@Override
			public void accept(Alert a) {
			}
		}, DialogButton.OK);
    }

    public String getText() {
    	Alert alert = getMasterDriver().switchTo().alert();
        return alert.getText();
    }
}
