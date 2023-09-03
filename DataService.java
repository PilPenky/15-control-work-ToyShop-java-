import java.io.IOException;

public interface DataService {
    void addNewToy(Toy toy) throws IOException;
    void changesWeightToy(Integer id, Double weight) throws IOException;
}
