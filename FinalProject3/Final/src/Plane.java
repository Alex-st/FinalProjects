import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by alex on 6/3/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "model",
        "origin",
        "type",
        "parameters",
        "price"
})
public class Plane {

        @XmlElement(required = true)
        protected String model;
        @XmlElement(required = true)
        protected String origin;
        @XmlElement(required = true)
        protected String type;
        @XmlElement(required = true)
        protected Params parameters;
        protected int price;
        @XmlAttribute(name = "id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Params", propOrder = {
            "length",
            "width",
            "height"
    })
        public static class Params {
            @XmlElement(name = "Length")
            protected int length;
            @XmlElement(name = "Width")
            protected int width;
            @XmlElement(name = "Height")
            protected int height;

            /**
             * Gets the value of the length property.
             *
             */
            public int getLength() {
                return length;
            }

            /**
             * Sets the value of the length property.
             *
             */
            public void setLength(int value) {
                this.length = value;
            }

            /**
             * Gets the value of the width property.
             *
             */
            public int getWidth() {
                return width;
            }

            /**
             * Sets the value of the width property.
             *
             */
            public void setWidth(int value) {
                this.width = value;
            }

            /**
             * Gets the value of the height property.
             *
             */
            public int getHeight() {
                return height;
            }

            /**
             * Sets the value of the height property.
             *
             */
            public void setHeight(int value) {
                this.height = value;
            }
        }

        public String getModel() {
            return model;
        }

        public void setModel(String value) {
            this.model = value;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String value) {
            this.origin = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String value) {
            this.type = value;
        }

        public Params getParameters() {
            return parameters;
        }

        public void setParameters(Params value) {
            this.parameters = value;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int value) {
            this.price = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }

}

