package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
        	name.setValue("Juanl");
        	
            /*layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));*/
        });
        
        Button miBoton = new Button("Este botón no hace nada de nada");
        miBoton.addClickListener(
			
			event ->{
			name.setValue("Adrián");
			}
			    /*layout.addComponent(new Label("Thanks " + name.getValue() 
                + ", it works!"))*/
				
			
		);
  
     /**
     * Updates the sample caption
     */

 
 
        name.addValueChangeListener(event -> Notification.show("Vaor cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        
        layout.addComponents(name, button, miBoton);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
