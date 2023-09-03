public abstract class AbstractToy {
    int toyId;
    String toyName;
    int toyCount;
    Double toyWeight;

    public AbstractToy(int toyId, String toyName, int toyCount, Double toyWeight) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.toyCount = toyCount;
        this.toyWeight = toyWeight;
    }
    public int getToyId() {
        return toyId;
    }
    public void setToyId(int toyId) {
        this.toyId = toyId;
    }
    public String getToyName() {
        return toyName;
    }
    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
    public int getToyCount() {
        return toyCount;
    }
    public void setToyCount(int toyCount) {
        this.toyCount = toyCount;
    }
    public Double getToyWeight() {
        return toyWeight;
    }
    public void setToyWeight(Double toyWeight) {
        this.toyWeight = toyWeight;
    }
}
