import java.util.ArrayList;
import java.util.List;

public class RaffleToy {
    ToyService toyService = new ToyService();

    public void getRaffle() throws Exception {
        toWaitingList(searchPercent(interestCollection("AllToys.txt")));
    }

    public void toWaitingList(int ind) throws Exception {
        if (ind < 0) {
            if (ind == -1) {
                toWaitingList(searchPercent(interestCollection("AllToys.txt")));
            }
            if (ind == -2) {
                throw new MyNoToysException("Ошибка! Игрушек больше нет.");
            }
        } else {
            String[][] allToysArray = toyService.readFile("AllToys.txt");
            List<String> arrData = new ArrayList();
            int index = toyService.searchId("Waiting list.txt");
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    arrData.add(String.valueOf(index));
                } else if (i == 2) {
                    arrData.add(String.valueOf(1));
                } else arrData.add(allToysArray[ind + 1][i]);
            }
            toyService.writeFile("Waiting list.txt", arrData);
            toyService.changesCountToy("AllToys.txt", ind + 1);
        }
    }

    public List interestCollection(String file) throws Exception {
        String[][] twoDimArray = toyService.readFile(file);
        List<Double> interestArrList = new ArrayList<Double>();
        List<Integer> arrListCount = new ArrayList<Integer>();
        String ex = "";

        for (int i = 1; i < twoDimArray.length; i++) {
            ex = twoDimArray[i][2];
            try {
                arrListCount.add(Integer.valueOf(twoDimArray[i][2]));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Неверный формат количества игрушек: \"" + ex + "\". Необходим целочисленный формат.");
            }
            try {
                if (arrListCount.get(0) >= 1) {
                    ex = twoDimArray[i][3];
                    interestArrList.add(Double.valueOf(twoDimArray[i][3]));
                } else {
                    interestArrList.add(-101.0);
                }
                arrListCount.remove(0);
            } catch (Exception e) {
                throw new MyNumberFormatException("Ошибка! Неверный формат веса игрушек: \"" + ex + "\". Необходим дробный формат, например 1.0.");
            }
        }
        return interestArrList;
    }

    public static int searchPercent(List<Double> interest) {
        ArrayList<Double> interestArrList = new ArrayList<Double>(interest);
        int num = 0;
        int res = -1;
        int checkNum = 0;
        for (int i = 0; i < interest.size(); i++) {
            num = (int) (Math.random() * 100) + 1;
            if (num > 0 && num <= interestArrList.get(i)) {
                res = i;
                return res;
            }
            if (interestArrList.get(i) == -101.0) {
                checkNum += 1;
            }
        }
        if (checkNum == interestArrList.size()) {
            return -2;
        }
        return res;
    }
}