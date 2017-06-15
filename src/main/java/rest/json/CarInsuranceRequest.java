package rest.json;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 14.06.2017.
 */
@XmlRootElement(name ="carInsuranceRequest")
public class CarInsuranceRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
