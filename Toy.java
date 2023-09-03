public class Toy extends AbstractToy{
    public Toy(int toyId, String toyName, int toyCount, Double toyWeight) {
        super(toyId, toyName, toyCount, toyWeight);
    }

    @Override
    public String toString() {
        return  "ID-игрушки: " + toyId + "; " +
                "Название игрушки: '" + toyName + "\'; " +
                "Количество игрушек: " + toyCount + " шт.; " +
                "Вес одной игрушки: " + toyWeight + " гр.";
    }
}