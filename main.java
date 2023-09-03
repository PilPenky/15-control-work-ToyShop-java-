import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        try {
            ToyService toyService = new ToyService();
            RaffleToy raffleToy = new RaffleToy();

            toyService.addNewToy(new Toy(toyService.searchId("AllToys.txt"), "Собачка", 25, 40.0));
            toyService.changesWeightToy(4, 55.0);
            raffleToy.getRaffle();
            raffleToy.getRaffle();
            raffleToy.getRaffle();
            toyService.getToy();
            toyService.getToy();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}