package Model;

public class PICtoGIM_Model {
    private String partnerCode;
    private String gmCode;
    private String description;

    public PICtoGIM_Model(String partnerCode, String gmCode, String description){
        this.partnerCode = partnerCode;
        this.gmCode = gmCode;
        this.description = description;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getGmCode() {
        return gmCode;
    }

    public void setGmCode(String gmCode) {
        this.gmCode = gmCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
