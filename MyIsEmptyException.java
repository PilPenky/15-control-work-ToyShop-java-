public class MyIsEmptyException extends IllegalArgumentException{
    public MyIsEmptyException() {
        super("Ошибка! Не удается выдать игрушку, так как лист ожидания игрушек пустой.");
    }
}
