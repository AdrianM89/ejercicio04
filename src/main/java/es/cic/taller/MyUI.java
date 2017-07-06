package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.HasValue.ValueChangeEvent;
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
        Label counter = new Label ("0 / " + name.getMaxLength());
        name.setCaption("Nombre:");
        name.setMaxLength(30);
        name.setPlaceholder("Dame el nombre");     
        name.addValueChangeListener(event -> {
        	listenerDeCambio(event, counter, name);
        });
        
        final TextField apellido = new TextField();
        Label counter1 = new Label ("0 / " + apellido.getMaxLength());
        apellido.setCaption("Apellido:");
        apellido.setMaxLength(30);
        apellido.addValueChangeListener(event -> {
        listenerDeCambio(event, counter1, apellido);
        });
        
        final TextField edad = new TextField();
        Label counter2 = new Label ("0 / " + edad.getMaxLength());
        edad.setCaption("Edad:");
        edad.setMaxLength(30);
        edad.addValueChangeListener(event -> {
        listenerDeCambio(event, counter2, edad);
        });
        
        
        final TextField direccion = new TextField();
        Label counter3 = new Label ("0 / " + direccion.getMaxLength());
        direccion.setCaption("Dirección:");
        direccion.setMaxLength(30);
        direccion.addValueChangeListener(event -> {
        listenerDeCambio(event, counter3, direccion);
        });

        Button button = new Button("Introducir datos");
        button.addClickListener( e -> {
        	
        	
        layout.addComponent(new Label(calcularDatos(name, apellido, edad)));
        });

 
 
        name.addValueChangeListener(event -> Notification.show("Valor cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        apellido.addValueChangeListener(event -> Notification.show("Valor cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        edad.addValueChangeListener(event -> Notification.show("Valor cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        direccion.addValueChangeListener(event -> Notification.show("Valor cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        
        layout.addComponents(name, apellido, edad, direccion, button, counter, counter1, counter2, counter3);
        
        setContent(layout);
    }

	private String calcularDatos(final TextField name, final TextField apellido, final TextField edad) {
		DatosPersonales datos = new DatosPersonales();
		datos.setNombre(name.getValue());
		datos.setApellido(apellido.getValue());
		datos.setEdad(edad.getValue());
		
		
		return "Hola " + datos.getNombre() + " " + datos.getApellido() + ", con edad " + datos.getEdad();
	}
    
    private void listenerDeCambio(ValueChangeEvent<String> event, Label counterFinal, TextField CuadroDeTexto) {
    	int len = event.getValue().length();
    	counterFinal.setValue(len + " de " + CuadroDeTexto.getMaxLength());
    	
    }
    
    
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
