package rest.json;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 14.06.2017.
 */
@XmlRootElement(name ="carInsuranceResponse")
public class CarInsuranceResponse {

    private int id;
    private String name;
    private String description = "You bought the insurance";


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    CarInsuranceRequest request = new CarInsuranceRequest();




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
