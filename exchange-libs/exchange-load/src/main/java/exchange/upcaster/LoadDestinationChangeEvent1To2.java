package exchange.upcaster;

import exchange.load.events.LoadDestinationChangedEvent;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.dom4j.Element;
import org.dom4j.Node;

//Upcaster from revision 1 to revision 2
public class LoadDestinationChangeEvent1To2 extends SingleEventUpcaster {

    private static SimpleSerializedType targetType =
            new SimpleSerializedType(LoadDestinationChangedEvent.class.getTypeName(), "1.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(targetType);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateRepresentation) {

        System.out.println("Upcasting from 1 to 2");
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(targetType.getName(), "2.0"),
                org.dom4j.Document.class,
                (document) -> {
                    Node node = document.selectSingleNode("//exchange.load.events.LoadDestinationChangedEvent//newDestination");
                    String newDestinationValue = node.getStringValue();
                    Element rootElement = document.getRootElement();
                    rootElement
                            .addElement("to")
                            .setText(newDestinationValue);
                    return document;
                }
        );
    }
}
